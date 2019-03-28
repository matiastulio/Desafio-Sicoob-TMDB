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
    private String baseImageRequestPath;
    private String imageSize;
    private String regiao;
    private boolean isStoredData;
}
