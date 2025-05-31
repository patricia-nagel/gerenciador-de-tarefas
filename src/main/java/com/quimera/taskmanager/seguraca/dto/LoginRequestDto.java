package com.quimera.taskmanager.seguraca.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequestDto {

    private String email; //pode ser o nick
    private String senha;
}
