package org.br.callForPaper.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //será mapeada
@Table(name = "users")
@Data //reduz boilerplate
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String cpf;

    @NotBlank(message = "o conteúdo é obrigatório")
    private String nome;

    @NotBlank(message = "o conteúdo é obrigatório")
    private String email;

}
