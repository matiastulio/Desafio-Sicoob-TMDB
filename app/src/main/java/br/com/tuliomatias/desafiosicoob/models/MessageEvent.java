package br.com.tuliomatias.desafiosicoob.models;

import java.util.ArrayList;

public class MessageEvent {
    public final boolean isImageUpdated;
    public final ArrayList<Filme> filmes;

    public MessageEvent(boolean isImageUpdated,ArrayList<Filme> filmes) {
        this.isImageUpdated = isImageUpdated;
        this.filmes = filmes;
    }
}