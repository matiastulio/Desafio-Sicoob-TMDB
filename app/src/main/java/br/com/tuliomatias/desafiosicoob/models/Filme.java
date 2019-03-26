package br.com.tuliomatias.desafiosicoob.models;


import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Filme {

    @SerializedName("title")
    private String titulo;

    @SerializedName("poster_path")
    private String imagePath;

    @SerializedName("overview")
    private String sinopse;

    @SerializedName("vote_count")
    private int voteCount;

    private int id;

    private boolean video;

    @SerializedName("vote_average")
    private float voteAverage;

    private float popularity;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private int[] generosIds;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private boolean adult;

    @SerializedName("release_date")
    private String dataLancamento;

}
