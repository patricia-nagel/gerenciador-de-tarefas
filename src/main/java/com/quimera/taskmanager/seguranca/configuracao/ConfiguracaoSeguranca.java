package com.quimera.taskmanager.seguranca.configuracao;

import com.quimera.taskmanager.seguranca.filtro.AutenticacaoFiltro;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SecurityScheme(name = ConfiguracaoSeguranca.SECURITY, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class ConfiguracaoSeguranca {

    public static final String SECURITY = "bearerAuth";

    @Autowired
    private AutenticacaoFiltro autenticacaoFiltro;

    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/auth/login", //url que usaremos para fazer login
            "/users", //url que usaremos para criar um usuário
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "swagger-ui.html"
    };

    // Endpoints que requerem autenticação para serem acessados
    public static final String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/tasks"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable) // Desativa a proteção contra CSRF
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configura a política de criação de sessão como stateless
                .authorizeHttpRequests(req -> { // Habilita a autorização para as requisições HTTP
                    req.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).permitAll();
                    req.requestMatchers(ENDPOINTS_WITH_AUTHENTICATION_REQUIRED).authenticated();
                    req.anyRequest().denyAll();
                })
                // Adiciona o filtro de autenticação de usuário que criamos, antes do filtro de segurança padrão do Spring Security
                .addFilterBefore(autenticacaoFiltro, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
