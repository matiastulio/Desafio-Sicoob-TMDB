package br.com.tuliomatias.desafiosicoob.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Tmdb {

    private int numeroPaginaAtual;
    private String lingua;
    private String apiKey;
    private String baseRequestPath;
}
