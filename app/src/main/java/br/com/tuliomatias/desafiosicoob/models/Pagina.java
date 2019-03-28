package br.com.tuliomatias.desafiosicoob.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pagina {

    @SerializedName("page")
    private int numero;

    @SerializedName("total_results")
    private int qtdResultados;

    @SerializedName("total_pages")
    private int qtdTotalPaginas;

    @SerializedName("results")
    ArrayList<Filme> filmes;



}
