package br.com.tuliomatias.desafiosicoob.tmdb;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.models.Filme;

public interface IRespostaDaApiTmdb {

    public abstract void respostaDaApi(ArrayList<Filme> filmes);

    public abstract String getPathPesquisa();
}
