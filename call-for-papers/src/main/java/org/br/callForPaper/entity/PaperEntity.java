package org.br.callForPaper.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paper")
@Data
@NoArgsConstructor
public class PaperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o conteúdo é obrigatório")
    private String titulo;

    @NotBlank(message = "o conteúdo é obrigatório")
    private String resumo;

    @NotBlank(message = "o conteúdo é obrigatório")
    @Column(name = "nome_do_autor")
    private String nomeDoAutor;

    @NotBlank(message = "o E-mail é obrigatório")
    @Email(message = "E=mail inválido")
    private String email;



}