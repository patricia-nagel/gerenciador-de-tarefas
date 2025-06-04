package com.quimera.taskmanager.dominio.tarefa;

import com.quimera.taskmanager.dominio.tarefa.domain.Situacao;
import com.quimera.taskmanager.dominio.tarefa.domain.Tarefa;
import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.exception.TarefaNaoEncontradaException;
import com.quimera.taskmanager.dominio.tarefa.repository.TarefaRepository;
import com.quimera.taskmanager.dominio.tarefa.service.TarefaService;
import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    @Test
    void deveCriarTarefaComSucesso() {
        //Arrange
        LocalDateTime dataAtual = LocalDateTime.now();
        TarefaRequestDto dto = TarefaRequestDto.builder()
                .titulo("Título")
                .descricao("Descricao")
                .situacao(Situacao.NOVO)
                .dataInicio(dataAtual)
                .build();
        Tarefa tarefaSalva = criarTarefa(2L, "Título",
                "Descricao", Situacao.NOVO.getCodigoSituacao(), dataAtual, null, null);
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaSalva);

        //Act
        TarefaResponseDto response = tarefaService.criarTarefa(dto);

        //Assert
        assertNotNull(response);
        assertEquals("Título", response.getTitulo());
        assertEquals(Situacao.NOVO, response.getSituacao());
        verify(tarefaRepository).save(any(Tarefa.class));
    }

    @Test
    void deveBuscarTarefaPorId() {
        LocalDateTime dataAtual = LocalDateTime.now();
        Tarefa tarefaSalva = criarTarefa(12L, "Tarefa",
                "Descricao", Situacao.EM_ANDAMENTO.getCodigoSituacao(), dataAtual, null, null);
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefaSalva));

        TarefaResponseDto response = tarefaService.buscarTarefa(1L);

        assertNotNull(response);
        assertEquals("Tarefa", response.getTitulo());
        assertEquals(Situacao.EM_ANDAMENTO, response.getSituacao());
        verify(tarefaRepository).findById(any());
    }

    @Test
    void deveLancarExcecao_QuandoTarefaNaoEncontrada() {
        when(tarefaRepository.findById(99L)).thenReturn(Optional.empty());

        TarefaNaoEncontradaException exception = assertThrows(TarefaNaoEncontradaException.class,
                () -> tarefaService.buscarTarefa(99L)
        );

        assertEquals("Tarefa com id 99 não foi encontrada", exception.getMessage());
    }

    @Test
    void deveBuscarListaTarefasPorUsuarioId() {
        LocalDateTime dataAtual = LocalDateTime.now();
        Long usuarioId = 55L;
        Usuario usuario = Usuario.builder()
                .id(usuarioId)
                .build();
        Tarefa tarefaUm = criarTarefa(1L, "Título",
                "Descricao", Situacao.EM_ANDAMENTO.getCodigoSituacao(), dataAtual, null, usuario);
        Tarefa tarefaDois = criarTarefa(1L, "Título",
                "Descricao", Situacao.FINALIZADA.getCodigoSituacao(), dataAtual, null, usuario);
        when(tarefaRepository.findAllByUsuarioId(usuarioId)).thenReturn(List.of(tarefaUm, tarefaDois));

        List<TarefaResponseDto> response = tarefaService.buscarTarefasPorUsuario(usuarioId);

        assertNotNull(response);
        assertEquals(2, response.size());
        verify(tarefaRepository).findAllByUsuarioId(any());
    }

    @Test
    void deveRetornarListaVaziaQuandoUsuarioNaoPossuiTarefa() {
        Long usuarioId = 55L;
        when(tarefaRepository.findAllByUsuarioId(usuarioId)).thenReturn(new ArrayList<>());

        List<TarefaResponseDto> response = tarefaService.buscarTarefasPorUsuario(usuarioId);

        assertNotNull(response);
        assertEquals(0, response.size());
        verify(tarefaRepository).findAllByUsuarioId(any());
    }


    @Test
    void deveAtualizarTarefaComSucesso() {
        LocalDateTime dataAtual = LocalDateTime.now();
        Long usuarioId = 55L;
        Tarefa tarefaNova = criarTarefa(1L, "Título",
                "Descricao", Situacao.EM_ANDAMENTO.getCodigoSituacao(), dataAtual, null, null);

        Tarefa tarefaExistente = criarTarefa(1L, "Antigo Título",
                "Descricao", Situacao.EM_ANDAMENTO.getCodigoSituacao(), dataAtual, null, null);

        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefaExistente));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefaExistente);

        assertDoesNotThrow(() -> tarefaService.atualizarTarefa(1L, null));
        assertEquals("Novo Título", tarefaExistente.getTitulo());
    }
//
//    @Test
//    void deveExcluirTarefaComSucesso() {
//        when(tarefaRepository.existsById(1L)).thenReturn(true);
//
//        assertDoesNotThrow(() -> tarefaService.excluirTarefa(1L));
//        verify(tarefaRepository).deleteById(1L);
//    }
//
//    @Test
//    void deveLancarExcecaoAoExcluirTarefaInexistente() {
//        when(tarefaRepository.existsById(999L)).thenReturn(false);
//
//        assertThrows(TarefaNaoEncontradaException.class, () -> tarefaService.excluirTarefa(999L));
//    }

    private Tarefa criarTarefa(Long idTarefa, String titulo, String descricao, String situacao, LocalDateTime dataInicio,
                               LocalDateTime dataFim, Usuario usuario) {
        return Tarefa.builder()
                .id(idTarefa)
                .titulo(titulo)
                .descricao(descricao)
                .situacao(situacao)
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .usuario(usuario)
                .build();
    }

}
