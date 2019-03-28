package br.com.tuliomatias.desafiosicoob.models;

import java.util.ArrayList;

public class ApiMessageEvent {
    public final boolean isImageUpdated;
    public final ArrayList<Filme> filmes;

    public ApiMessageEvent(boolean isImageUpdated, ArrayList<Filme> filmes) {
        this.isImageUpdated = isImageUpdated;
        this.filmes = filmes;
    }
}