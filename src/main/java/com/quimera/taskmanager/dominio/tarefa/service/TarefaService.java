package com.quimera.taskmanager.dominio.tarefa.service;

import com.quimera.taskmanager.dominio.tarefa.domain.Tarefa;
import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.mapper.TarefaMapper;
import com.quimera.taskmanager.dominio.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaResponseDto criarTarefa(TarefaRequestDto tarefaRequestDto) {
        Tarefa tarefa = TarefaMapper.toDomain(tarefaRequestDto);
        tarefaRepository.save(tarefa);
        return TarefaMapper.toDto(tarefa);
    }

    public TarefaResponseDto buscarTarefa(Long idTarefa) {
        Tarefa tarefa = tarefaRepository.findById(idTarefa).orElse(null);
        if (nonNull(tarefa)) {
            return TarefaMapper.toDto(tarefa);
        }
        return null;
    }

    public List<TarefaResponseDto> buscarTarefasPorUsuario(Long usuarioId) {
        List<Tarefa> tarefa = tarefaRepository.findAllByUsuarioId(usuarioId);
        return tarefa.stream()
                .map(TarefaMapper::toDto)
                .collect(Collectors.toList());
    }

    public void atualizarTarefa(Long id, TarefaRequestDto tarefaRequestDto) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrada"));

        tarefa.setTitulo(tarefaRequestDto.getTitulo());
        tarefa.setDescricao(tarefaRequestDto.getDescricao());
        tarefa.setDataFim(tarefaRequestDto.getDataFim());
        tarefa.setDataInicio(tarefaRequestDto.getDataInicio());
        //Como trocaria o usuario com JPA?
        tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }

}
