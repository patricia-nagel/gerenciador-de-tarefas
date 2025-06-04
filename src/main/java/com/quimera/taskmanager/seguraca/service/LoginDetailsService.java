package com.quimera.taskmanager.seguraca.service;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.repository.UsuarioRepository;
import com.quimera.taskmanager.seguraca.details.LoginDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsuario(username).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return new LoginDetails(usuario);
    }
}
