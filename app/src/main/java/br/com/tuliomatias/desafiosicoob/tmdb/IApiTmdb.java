package br.com.tuliomatias.desafiosicoob.tmdb;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.models.Filme;

public interface IApiTmdb {

    public void getFilmes();
    public void getImageFromPath( String relativePath);// retorna o base64 da imagem
    public void getFilme();
}
