package com.quimera.taskmanager.dominio.tarefa.service;

import com.quimera.taskmanager.dominio.tarefa.domain.Tarefa;
import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.exception.TarefaNaoEncontradaException;
import com.quimera.taskmanager.dominio.tarefa.mapper.TarefaMapper;
import com.quimera.taskmanager.dominio.tarefa.repository.TarefaRepository;
import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaResponseDto criarTarefa(TarefaRequestDto tarefaRequestDto) {
        Tarefa tarefa = tarefaRepository.save(TarefaMapper.toDomain(tarefaRequestDto));
        log.info("Tarefa {} - {} criada com sucesso!", tarefa.getId(), tarefa.getTitulo());
        return TarefaMapper.toDto(tarefa);
    }

    public TarefaResponseDto buscarTarefa(Long idTarefa) {
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new TarefaNaoEncontradaException(idTarefa));

        return TarefaMapper.toDto(tarefa);
    }

    public List<TarefaResponseDto> buscarTarefasPorUsuario(Long usuarioId) {
        List<Tarefa> tarefas = tarefaRepository.findAllByUsuarioId(usuarioId);

        if (tarefas.isEmpty()) {
            log.info("Nenhuma tarefa atribuída ao usuário com ID {}", usuarioId);
        }

        return tarefas.stream()
                .map(TarefaMapper::toDto)
                .collect(Collectors.toList());
    }

    public void atualizarTarefa(Long idTarefa, TarefaRequestDto tarefaRequestDto) {
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new TarefaNaoEncontradaException(idTarefa));

        tarefa.setTitulo(tarefaRequestDto.getTitulo());
        tarefa.setDescricao(tarefaRequestDto.getDescricao());
        tarefa.setDataFim(tarefaRequestDto.getDataFim());
        tarefa.setDataInicio(tarefaRequestDto.getDataInicio());
        atribuirUsuarioATarefa(tarefa, tarefaRequestDto.getIdUsuario());

        tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new TarefaNaoEncontradaException(id);
        }

        tarefaRepository.deleteById(id);
    }

    private void atribuirUsuarioATarefa(Tarefa tarefa, Long usuarioId) {
        if (nonNull(usuarioId)) {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            tarefa.setUsuario(usuario);
        }
    }

}
