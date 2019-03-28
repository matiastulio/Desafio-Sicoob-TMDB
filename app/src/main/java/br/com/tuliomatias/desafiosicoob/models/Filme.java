package br.com.tuliomatias.desafiosicoob.models;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
@Entity(tableName = "FILMES")
public class Filme {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    private String titulo;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String imagePath;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String sinopse;

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    private int voteCount;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private float voteAverage;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String originalLanguage;

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    private String originalTitle;

    @Ignore
    @SerializedName("genre_ids")
    private int[] generosIds;

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    private String backdropPath;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String dataLancamento;

    @Ignore
    private Bitmap image;

    private String budget;

    @Ignore
    private ArrayList<Genero> genres;

    private int runtime;

    private int revenue;

    private float popularity;

    private boolean video;

    private boolean adult;


    public Filme(){}
}
