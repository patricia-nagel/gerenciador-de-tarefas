package com.quimera.taskmanager.dominio.usuario.service;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.exception.UsuarioNaoEncontradoException;
import com.quimera.taskmanager.dominio.usuario.mapper.UsuarioMapper;
import com.quimera.taskmanager.dominio.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDto criarUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.save(UsuarioMapper.toDomain(usuarioRequestDto));
        log.info("Usuário {} - {} criado com sucesso!", usuario.getId(), usuario.getUsuario());
        return UsuarioMapper.toDto(usuario);
    }

    public UsuarioResponseDto buscarUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));

        return UsuarioMapper.toDto(usuario);
    }

    public void atualizarUsuario(Long idUsuario, UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));

        usuario.setUsuario(usuarioRequestDto.getUsuario());
        usuario.setEmail(usuarioRequestDto.getEmail());
        usuario.setDataNascimento(usuarioRequestDto.getDataNascimento());
        usuario.setNomeCompleto(usuarioRequestDto.getNomeCompleto());
        usuario.setDataNascimento(usuarioRequestDto.getDataNascimento());

        usuarioRepository.save(usuario);
    }

    public void softDelete(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));

        usuario.setLoginAtivo("N");
        usuarioRepository.save(usuario);
    }

}
