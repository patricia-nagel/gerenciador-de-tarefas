package com.quimera.taskmanager.seguranca.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDto {

    private String token;
}
