package br.com.tuliomatias.desafiosicoob.connection;

import com.squareup.okhttp.OkHttpClient;

import lombok.Getter;

public class OkHttpClientSingleton {

    private static OkHttpClientSingleton instancia = new OkHttpClientSingleton();

    @Getter private OkHttpClient client;

    private OkHttpClientSingleton(){
        client = new OkHttpClient();
    }

    public static OkHttpClientSingleton getInstance(){
        return instancia;
    }
}
