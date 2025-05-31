package com.quimera.taskmanager.dominio.tarefa.controller;

import com.quimera.taskmanager.dominio.tarefa.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping()
    public void criarTarefa() {
        //POST /tasks Criar uma nova tarefa.
    }

    @GetMapping("/{id}")
    public void buscarInformacoesTarefa() {
        //GET /tasks/{id} Obter detalhes de uma tarefa.
    }

    @GetMapping()
    public void buscarTarefasPorUsuario(@RequestParam Long assignedTo) {
        //GET /tasks?assignedTo={userId} Listar todas as tarefas atribuídas a um usuário.
    }

    @PutMapping("/{id}")
    public void atualizarInformacoesTarefa() {
        //PUT /tasks/{id} Atualizar informações da tarefa (título, descrição, status).
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa() {
        //DELETE /tasks/{id} Remover uma tarefa.
    }
}
