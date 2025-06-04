package com.quimera.taskmanager.dominio.tarefa.service;

import com.quimera.taskmanager.dominio.tarefa.domain.Situacao;
import com.quimera.taskmanager.dominio.tarefa.domain.Tarefa;
import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.exception.TarefaNaoEncontradaException;
import com.quimera.taskmanager.dominio.tarefa.repository.TarefaRepository;
import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa;
    private TarefaRequestDto requestDto;

    @BeforeEach
    void setUp() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        tarefa = Tarefa.builder()
                .id(1L)
                .titulo("Teste")
                .descricao("Descrição teste")
                .situacao("NOVO")
                .dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().plusDays(1))
                .usuario(usuario)
                .build();

        requestDto = TarefaRequestDto.builder()
                .titulo("Teste")
                .descricao("Descrição teste")
                .situacao(null)
                .idUsuario(1L)
                .dataInicio(LocalDateTime.now())
                .dataFim(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    void deveCriarTarefa() {
        requestDto.setSituacao(Situacao.NOVO);
        tarefa.setSituacao("1");
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        TarefaResponseDto response = tarefaService.criarTarefa(requestDto);

        assertNotNull(response);
        assertEquals(tarefa.getId(), response.getId());
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void deveBuscarTarefaExistente() {
        requestDto.setSituacao(Situacao.NOVO);
        tarefa.setSituacao("1");
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));

        TarefaResponseDto response = tarefaService.buscarTarefa(1L);

        assertNotNull(response);
        assertEquals(tarefa.getId(), response.getId());
        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTarefaNaoExistir() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> tarefaService.buscarTarefa(1L));
        verify(tarefaRepository, times(1)).findById(1L);
    }

    @Test
    void deveBuscarTarefasPorUsuario() {
        requestDto.setSituacao(Situacao.NOVO);
        tarefa.setSituacao("1");
        List<Tarefa> lista = List.of(tarefa);
        when(tarefaRepository.findAllByUsuarioId(1L)).thenReturn(lista);

        List<TarefaResponseDto> tarefas = tarefaService.buscarTarefasPorUsuario(1L);

        assertFalse(tarefas.isEmpty());
        assertEquals(1, tarefas.size());
        verify(tarefaRepository, times(1)).findAllByUsuarioId(1L);
    }

    @Test
    void deveAtualizarTarefaExistente() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(any(Tarefa.class))).thenReturn(tarefa);

        tarefaService.atualizarTarefa(1L, requestDto);

        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaRepository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void deveLancarExcecaoAoAtualizarTarefaInexistente() {
        when(tarefaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(TarefaNaoEncontradaException.class, () -> tarefaService.atualizarTarefa(1L, requestDto));
        verify(tarefaRepository, times(1)).findById(1L);
        verify(tarefaRepository, never()).save(any());
    }

    @Test
    void deveExcluirTarefaExistente() {
        when(tarefaRepository.existsById(1L)).thenReturn(true);

        tarefaService.excluirTarefa(1L);

        verify(tarefaRepository, times(1)).existsById(1L);
        verify(tarefaRepository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirTarefaInexistente() {
        when(tarefaRepository.existsById(1L)).thenReturn(false);

        assertThrows(TarefaNaoEncontradaException.class, () -> tarefaService.excluirTarefa(1L));

        verify(tarefaRepository, times(1)).existsById(1L);
        verify(tarefaRepository, never()).deleteById(anyLong());
    }
}

