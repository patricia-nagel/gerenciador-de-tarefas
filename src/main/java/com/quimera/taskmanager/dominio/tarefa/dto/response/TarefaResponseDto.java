package com.quimera.taskmanager.dominio.tarefa.dto.response;

import com.quimera.taskmanager.dominio.tarefa.domain.Situacao;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaResponseDto {

    private Long id;
    private String titulo;
    private String descricao;
    private Situacao situacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String responsavel;

}
