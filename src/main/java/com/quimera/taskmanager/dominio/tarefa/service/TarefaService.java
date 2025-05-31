package com.quimera.taskmanager.dominio.tarefa.service;

import com.quimera.taskmanager.dominio.tarefa.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

}
