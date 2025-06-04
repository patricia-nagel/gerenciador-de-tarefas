package com.quimera.taskmanager.configuracao;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SecurityScheme(name = SecurityConfigSwagger.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfigSwagger {
    //TODO[patricia]: classe para mesclar com a classe correta
    //scheme é o início do token

    public static final String SECURITY = "bearerAuth";

    //permitir o swagger no security filter
    //.requestMatchers("/v3/api-docs/**", "swagger-ui/**", "swagger-ui.html").permitAll()
}
