package com.quimera.taskmanager.dominio.tarefa.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaResponseDto {

    private String titulo;
    private String descricao;
    private String situacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String responsavel;

}
