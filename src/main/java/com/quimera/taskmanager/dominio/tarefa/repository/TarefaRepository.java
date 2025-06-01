package com.quimera.taskmanager.dominio.tarefa.repository;

import com.quimera.taskmanager.dominio.tarefa.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findAllByUsuarioId(Long usuarioId);
}
