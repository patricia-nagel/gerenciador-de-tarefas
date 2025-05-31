package com.quimera.taskmanager.dominio.tarefa.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarefa {
    //Ajustar para classe de entidade
    private Long id;
    private String titulo;
    private String descricao;
    private String situacao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
