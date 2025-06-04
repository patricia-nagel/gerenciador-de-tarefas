package com.quimera.taskmanager.seguranca.filtro;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.repository.UsuarioRepository;
import com.quimera.taskmanager.seguranca.details.LoginDetails;
import com.quimera.taskmanager.seguranca.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class AutenticacaoFiltro extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService; // Service que definimos anteriormente

    @Autowired
    private UsuarioRepository usuarioRepository; // Repository que definimos anteriormente

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Verifica se o endpoint requer autenticação antes de processar a requisição

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = recoveryToken(request); // Recupera o token do cabeçalho Authorization da requisição
            if (token != null) {
                String subject = jwtService.getSubjectFromToken(token); // Obtém o assunto (neste caso, o nome de usuário) do token
                Usuario user = usuarioRepository.findByUsuario(subject).get(); // Busca o usuário pelo email (que é o assunto do token)
                LoginDetails userDetails = new LoginDetails(user); // Cria um LoginDetails com o usuário encontrado

                // Cria um objeto de autenticação do Spring Security
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());

                // Define o objeto de autenticação no contexto de segurança do Spring Security
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new RuntimeException("O token está ausente.");
            }
        }
        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }

    // Recupera o token do cabeçalho Authorization da requisição
    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

}
