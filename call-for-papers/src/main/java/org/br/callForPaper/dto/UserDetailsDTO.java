package org.br.callForPaper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;

    @NotBlank(message = "o conteúdo é obrigatório")
    @JsonProperty("nome")
    private String nome;

    @NotBlank(message = "o conteúdo é obrigatório")
    @JsonProperty("email")
    private String email;

}
