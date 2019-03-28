package br.com.tuliomatias.desafiosicoob.tmdb;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.greenrobot.eventbus.EventBus;


import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.connection.ICallbackFromRequest;
import br.com.tuliomatias.desafiosicoob.connection.RequestHttp;
import br.com.tuliomatias.desafiosicoob.models.Filme;
import br.com.tuliomatias.desafiosicoob.models.FilmeMessageEvent;
import br.com.tuliomatias.desafiosicoob.models.ApiMessageEvent;
import br.com.tuliomatias.desafiosicoob.models.Pagina;
import br.com.tuliomatias.desafiosicoob.models.Tmdb;

public class ApiTmdb implements IApiTmdb, ICallbackFromRequest {

    private Tmdb config;
    private RequestHttp carregador;
    private IRespostaDaApiTmdb solicitante;

    public ApiTmdb(Tmdb config,IRespostaDaApiTmdb solicitante) {
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
    public void getFilme(int filmeId) {
        carregador.requestMovie(filmeId);
    }

    @Override
    public void onResponseOkFromCall(String corpoResposta) {
        Gson gson = new GsonBuilder().create();

        Pagina p = gson.fromJson(corpoResposta, Pagina.class);
        if(p.getFilmes() != null){// é uma lista de filmes
            config.setNumeroPaginaAtual(config.getNumeroPaginaAtual()+1);

            ArrayList<Filme> filmes = p.getFilmes();

            EventBus.getDefault().post(new ApiMessageEvent(false, filmes));

            for(Filme f: filmes){
                getImageFromPath(f);
            }
        }else{// é um filme unico
            Filme f = gson.fromJson(corpoResposta, Filme.class);
            EventBus.getDefault().post(new FilmeMessageEvent(f));
        }

    }

    private void getListaFromPath(String relativeUrl){
        carregador.requestMovies(relativeUrl);
    }
}
