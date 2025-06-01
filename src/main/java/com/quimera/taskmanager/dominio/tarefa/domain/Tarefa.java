package com.quimera.taskmanager.dominio.tarefa.domain;


import com.quimera.taskmanager.dominio.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String titulo;

    private String descricao;

    private String situacao;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

}
