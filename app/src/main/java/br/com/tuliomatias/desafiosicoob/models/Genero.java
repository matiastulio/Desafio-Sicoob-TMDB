package br.com.tuliomatias.desafiosicoob.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Genero {
    private int id;
    private String name;

}
