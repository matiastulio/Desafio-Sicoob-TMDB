package br.com.tuliomatias.desafiosicoob.models;

import java.util.ArrayList;

public class MessageEvent {
    public final ImagemDTO dto;
    public final ArrayList<Filme> filmes;

    public MessageEvent(ImagemDTO dto,ArrayList<Filme> filmes) {
        this.dto = dto;
        this.filmes = filmes;
    }
}