package com.quimera.taskmanager.dominio.usuario.mapper;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;

public class UsuarioMapper {

    public static Usuario toDomain(UsuarioRequestDto usuarioRequestDto) {
        return Usuario.builder()
                .usuario(usuarioRequestDto.getUsuario())
                .senha(usuarioRequestDto.getSenha())
                .email(usuarioRequestDto.getEmail())
                .nomeCompleto(usuarioRequestDto.getNomeCompleto())
                .dataNascimento(usuarioRequestDto.getDataNascimento())
                .build();
    }

    public static UsuarioResponseDto toDto(Usuario usuario) {
        return UsuarioResponseDto.builder()
                .usuario(usuario.getUsuario())
                .email(usuario.getEmail())
                .nomeCompleto(usuario.getNomeCompleto())
                .dataNascimento(usuario.getDataNascimento())
                .build();
    }
}
