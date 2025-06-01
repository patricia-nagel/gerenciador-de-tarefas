package com.quimera.taskmanager.dominio.tarefa.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaRequestDto {

    private String titulo;
    private String descricao;
    private String situacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
