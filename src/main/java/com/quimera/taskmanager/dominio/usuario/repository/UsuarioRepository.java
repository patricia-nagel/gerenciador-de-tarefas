package com.quimera.taskmanager.dominio.usuario.repository;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
