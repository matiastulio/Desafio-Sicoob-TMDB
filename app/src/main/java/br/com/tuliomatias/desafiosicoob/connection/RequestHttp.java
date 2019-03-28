package br.com.tuliomatias.desafiosicoob.connection;


import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import br.com.tuliomatias.desafiosicoob.R;
import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.models.ApiMessageEvent;
import br.com.tuliomatias.desafiosicoob.models.Tmdb;

public class RequestHttp implements Callback {

    private Tmdb config;
    private ICallbackFromRequest solicitante;

    private static String PAGE = "page";
    private static String LANGUAGE = "language";
    private static String API_KEY = "api_key";
    private static String REGION = "region";

    public RequestHttp(Tmdb config,ICallbackFromRequest solicitante) {
        this.config = config;
        this.solicitante = solicitante;
    }


    public void requestMovies(String relativeUrl){

        HttpUrl.Builder urlBuilder = HttpUrl.parse(config.getBaseRequestPath()+relativeUrl).newBuilder();
        urlBuilder.addQueryParameter(PAGE,String.valueOf(config.getNumeroPaginaAtual()));
        urlBuilder.addQueryParameter(LANGUAGE,config.getLingua());
        urlBuilder.addQueryParameter(API_KEY,config.getApiKey());
        urlBuilder.addQueryParameter(REGION,config.getRegiao());

        Request request = new Request.Builder().url(urlBuilder.build().toString()).build();


        OkHttpClientSingleton.getInstance().getClient().newCall(request).enqueue(this);

    }

    public void requestImage(Filme f){
        try {
            f.setImage(Picasso.get()
                    .load(config.getBaseImageRequestPath()+config.getImageSize()+f.getImagePath())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .get());
            EventBus.getDefault().post(new ApiMessageEvent(true,null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {

    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        } else {
            config.setNumeroPaginaAtual(config.getNumeroPaginaAtual()+1);
            solicitante.onResponseOkFromCall(response.body().string());
        }
    }
}