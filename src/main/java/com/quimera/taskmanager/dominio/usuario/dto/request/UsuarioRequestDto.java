package com.quimera.taskmanager.dominio.usuario.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequestDto {

    @NotBlank(message = "O nick de usuário é obrigatório.")
    @Size(max = 255, message = "O nick de usuário pode ter no máximo 255 caracteres.")
    private String usuario;

    @NotBlank(message = "A senha de usuário é obrigatória.")
    @Size(max = 255, message = "O senha de usuário pode ter no máximo 255 caracteres.")
    private String senha;

    @NotBlank(message = "O email de usuário é obrigatório.")
    @Size(max = 255, message = "O email de usuário pode ter no máximo 255 caracteres.")
    private String email;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 255, message = "O nome de usuário pode ter no máximo 255 caracteres.")
    private String nomeCompleto;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private LocalDate dataNascimento;

}
