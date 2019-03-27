package br.com.tuliomatias.desafiosicoob.tmdb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;


import br.com.tuliomatias.desafiosicoob.connection.ICallbackFromRequest;
import br.com.tuliomatias.desafiosicoob.connection.RequestHttp;
import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.models.MessageEvent;
import br.com.tuliomatias.desafiosicoob.models.Pagina;
import br.com.tuliomatias.desafiosicoob.models.Tmdb;

public class ApiTmdb implements IApiTmdb, ICallbackFromRequest {

    private Tmdb config;
    private RequestHttp carregador;
    private  IRespostaDaApiTmdb solicitante;

    public ApiTmdb(Tmdb config, IRespostaDaApiTmdb solicitante) {
        this.config = config;
        carregador = new RequestHttp(config,this);
        this.solicitante = solicitante;
    }

    @Override
    public void getFilmes() {
        getListaFromPath(solicitante.getPathPesquisa());
    }

    @Override
    public void getImageFromPath(Filme f) {
        carregador.requestImage(f);
    }

    @Override
    public void getFilme() {
    }

    @Override
    public void onResponseOkFromCall(String corpoResposta) {
        Gson gson = new GsonBuilder().create();

        Pagina p = gson.fromJson(corpoResposta, Pagina.class);

        EventBus.getDefault().post(new MessageEvent(false,p.getFilmes()));

        for(Filme f: p.getFilmes()){
            getImageFromPath(f);
        }
    }

    private void getListaFromPath(String relativeUrl){
        carregador.requestUrl(relativeUrl);
    }
}
