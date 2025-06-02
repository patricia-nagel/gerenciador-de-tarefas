package com.quimera.taskmanager.dominio.usuario.service;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.mapper.UsuarioMapper;
import com.quimera.taskmanager.dominio.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDto salvar(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.save(UsuarioMapper.toDomain(usuarioRequestDto));
        return UsuarioMapper.toDto(usuario);
        //Alterado para obter o id
    }

    public UsuarioResponseDto buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (nonNull(usuario)) {
            return UsuarioMapper.toDto(usuario);
        }
        return null;
    }

    public void atualizar(Long id, UsuarioRequestDto usuarioRequestDto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrada"));

        usuario.setUsuario(usuarioRequestDto.getUsuario());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setDataNascimento(usuarioRequestDto.getDataNascimento());
        usuario.setNomeCompleto(usuarioRequestDto.getNomeCompleto());
        usuario.setDataNascimento(usuarioRequestDto.getDataNascimento());

        usuarioRepository.save(usuario);
    }

    public void softDelete(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrada"));

        if (nonNull(usuario)) {
            usuario.setLoginAtivo("N");
        }

        usuarioRepository.save(usuario);
    }



}
