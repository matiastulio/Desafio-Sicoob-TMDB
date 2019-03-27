package br.com.tuliomatias.desafiosicoob.tmdb;

import android.widget.ImageView;

import java.util.ArrayList;

import br.com.tuliomatias.desafiosicoob.models.Filme;

public interface IApiTmdb {

    public void getFilmes();
    public void getImageFromPath(Filme filme);
    public void getFilme();
}
