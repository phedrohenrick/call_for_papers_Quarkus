package org.br.callForPaper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class PaperDetailsDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @NotBlank(message = "o conteúdo é obrigatório")
    @JsonProperty("titulo")
    private String titulo;

    @NotBlank(message = "o conteúdo é obrigatório")
    @JsonProperty("resumo")
    private String resumo;

    @NotBlank(message = "o conteúdo é obrigatório")
    @JsonProperty("nomeDoAutor")
    private String nomeDoAutor;

    @NotBlank(message = "o conteúdo é obrigatório")
    @JsonProperty("email")
    private String email;
}
