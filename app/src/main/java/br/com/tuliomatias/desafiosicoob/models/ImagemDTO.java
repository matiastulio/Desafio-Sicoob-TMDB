package br.com.tuliomatias.desafiosicoob.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ImagemDTO {

    private String base64;
    private String id;
}
