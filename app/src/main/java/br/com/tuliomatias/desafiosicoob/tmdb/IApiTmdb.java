package br.com.tuliomatias.desafiosicoob.tmdb;

import br.com.tuliomatias.desafiosicoob.models.Filme;

public interface IApiTmdb {

    public void getFilmes();
    public void getImageFromPath(Filme filme);
    public void getFilme(int filmeId);
}
