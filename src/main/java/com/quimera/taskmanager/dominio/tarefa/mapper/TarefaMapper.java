package com.quimera.taskmanager.dominio.tarefa.mapper;

import com.quimera.taskmanager.dominio.tarefa.domain.Situacao;
import com.quimera.taskmanager.dominio.tarefa.domain.Tarefa;
import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;

public class TarefaMapper {

    public static TarefaResponseDto toDto(Tarefa tarefa) {
        return TarefaResponseDto.builder()
                .id(tarefa.getId())
                .titulo(tarefa.getTitulo())
                .descricao(tarefa.getDescricao())
                .situacao(Situacao.fromCodigo(tarefa.getSituacao()))
                .dataInicio(tarefa.getDataInicio())
                .dataFim(tarefa.getDataFim())
                .build();
    }

    public static Tarefa toDomain(TarefaRequestDto tarefaRequestDto) {
        return Tarefa.builder()
                .titulo(tarefaRequestDto.getTitulo())
                .descricao(tarefaRequestDto.getDescricao())
                .situacao(tarefaRequestDto.getSituacao().getCodigoSituacao())
                .dataInicio(tarefaRequestDto.getDataInicio())
                .dataFim(tarefaRequestDto.getDataFim())
                .build();
    }
}
