package com.quimera.taskmanager.seguraca.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    private String usuario; //pode ser o nick
    private String senha;
}
