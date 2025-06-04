package com.quimera.taskmanager.dominio.usuario.service;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.exception.UsuarioNaoEncontradoException;
import com.quimera.taskmanager.dominio.usuario.mapper.UsuarioMapper;
import com.quimera.taskmanager.dominio.usuario.repository.UsuarioRepository;
import com.quimera.taskmanager.seguraca.configuracao.ConfiguracaoSeguranca;
import com.quimera.taskmanager.seguraca.details.LoginDetails;
import com.quimera.taskmanager.seguraca.dto.LoginRequestDto;
import com.quimera.taskmanager.seguraca.dto.LoginResponseDto;
import com.quimera.taskmanager.seguraca.exception.AutenticacaoException;
import com.quimera.taskmanager.seguraca.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtTokenService;

    @Autowired
    private ConfiguracaoSeguranca securityConfiguration;

    public UsuarioResponseDto criarUsuario(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioRepository.save(UsuarioMapper.toDomain(usuarioRequestDto, securityConfiguration));
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
        usuario.setSenha(usuarioRequestDto.getSenha());

        usuarioRepository.save(usuario);
    }

    public void softDelete(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));

        usuario.setLoginAtivo("N");
        usuarioRepository.save(usuario);
    }

    // Metodo responsável por autenticar um usuário e retornar um token JWT
    public LoginResponseDto autenticarUsuario(LoginRequestDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.getUsuario(), loginUserDto.getSenha());

        Authentication authentication;
        // Autentica o usuário com as credenciais fornecidas
        //Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        try {
            log.info("Tentando autenticar...");
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            log.info("Autenticou!");
        } catch (Exception e){
            log.info("Falha na autenticação! Erro: " + e.getMessage(), e);
            throw new AutenticacaoException();
        }

        // Obtém o objeto UserDetails do usuário autenticado
        LoginDetails userDetails = (LoginDetails) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new LoginResponseDto(jwtTokenService.generateToken(userDetails));
    }

}
