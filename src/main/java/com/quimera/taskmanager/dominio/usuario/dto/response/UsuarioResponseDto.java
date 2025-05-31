package com.quimera.taskmanager.dominio.usuario.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioResponseDto {

    private String usuario;
    private String email;
    private String nomeCompleto;
    private LocalDate dataNascimento;

}
