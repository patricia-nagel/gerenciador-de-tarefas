package com.quimera.taskmanager.dominio.usuario.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDto {

    private Long id;
    private String usuario;
    private String senha;
    private String email;
    private String loginAtivo;
    private String nomeCompleto;
    private LocalDate dataNascimento;

}
