package com.quimera.taskmanager.seguranca.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    private String usuario;
    private String senha;
}
