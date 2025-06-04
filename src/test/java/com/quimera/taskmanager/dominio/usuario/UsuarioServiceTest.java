package com.quimera.taskmanager.dominio.usuario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.exception.UsuarioNaoEncontradoException;
import com.quimera.taskmanager.dominio.usuario.repository.UsuarioRepository;
import com.quimera.taskmanager.dominio.usuario.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario_deveSalvarEretornarDto() {
        UsuarioRequestDto request = UsuarioRequestDto.builder()
                .usuario("usuario123")
                .email("user@example.com")
                .nomeCompleto("Usuário Teste")
                .senha("senha123")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        Usuario usuarioSalvo = Usuario.builder()
                .id(1L)
                .usuario(request.getUsuario())
                .email(request.getEmail())
                .nomeCompleto(request.getNomeCompleto())
                .senha(request.getSenha())
                .dataNascimento(request.getDataNascimento())
                .build();

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        UsuarioResponseDto response = usuarioService.criarUsuario(request);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("usuario123", response.getUsuario());

        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void buscarUsuario_comIdValido_deveRetornarDto() {
        Long id = 1L;
        Usuario usuario = Usuario.builder()
                .id(id)
                .usuario("usuario123")
                .email("user@example.com")
                .nomeCompleto("Usuário Teste")
                .senha("senha123")
                .dataNascimento(LocalDate.of(1990, 1, 1))
                .build();

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuario));

        UsuarioResponseDto dto = usuarioService.buscarUsuario(id);

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals("usuario123", dto.getUsuario());
    }

    @Test
    void buscarUsuario_comIdInvalido_deveLancarExcecao() {
        Long id = 999L;

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.buscarUsuario(id);
        });
    }

    @Test
    void atualizarUsuario_deveAtualizarESalvar() {
        Long id = 1L;
        Usuario usuarioExistente = Usuario.builder()
                .id(id)
                .usuario("usuarioAntigo")
                .email("antigo@example.com")
                .nomeCompleto("Usuário Antigo")
                .senha("senhaAntiga")
                .dataNascimento(LocalDate.of(1980, 1, 1))
                .build();

        UsuarioRequestDto requestAtualizacao = UsuarioRequestDto.builder()
                .usuario("usuarioNovo")
                .email("novo@example.com")
                .nomeCompleto("Usuário Novo")
                .senha("senhaNova")
                .dataNascimento(LocalDate.of(1995, 2, 2))
                .build();

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        usuarioService.atualizarUsuario(id, requestAtualizacao);

        // Verifica que o objeto usuarioExistente foi modificado
        assertEquals("usuarioNovo", usuarioExistente.getUsuario());
        assertEquals("novo@example.com", usuarioExistente.getEmail());
        assertEquals("Usuário Novo", usuarioExistente.getNomeCompleto());
        assertEquals("senhaNova", usuarioExistente.getSenha());
        assertEquals(LocalDate.of(1995, 2, 2), usuarioExistente.getDataNascimento());

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(usuarioExistente);
    }

    @Test
    void atualizarUsuario_comIdInvalido_deveLancarExcecao() {
        Long id = 999L;
        UsuarioRequestDto request = UsuarioRequestDto.builder().build();

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.atualizarUsuario(id, request);
        });
    }

    @Test
    void softDelete_deveAlterarLoginAtivoParaN() {
        Long id = 1L;
        Usuario usuarioExistente = Usuario.builder()
                .id(id)
                .loginAtivo("S")
                .build();

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.of(usuarioExistente));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        usuarioService.softDelete(id);

        assertEquals("N", usuarioExistente.getLoginAtivo());

        verify(usuarioRepository, times(1)).findById(id);
        verify(usuarioRepository, times(1)).save(usuarioExistente);
    }

    @Test
    void softDelete_comIdInvalido_deveLancarExcecao() {
        Long id = 999L;

        when(usuarioRepository.findById(id)).thenReturn(java.util.Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> {
            usuarioService.softDelete(id);
        });
    }

}

