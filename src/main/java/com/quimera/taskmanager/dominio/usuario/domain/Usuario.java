package com.quimera.taskmanager.dominio.usuario.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {
    //Ajustar para classe de entidade
    private Long id;
    private String usuario;
    private String senha;
    private String email;
    private String loginAtivo;
    private String nomeCompleto;
    private LocalDate dataNascimento;
}
