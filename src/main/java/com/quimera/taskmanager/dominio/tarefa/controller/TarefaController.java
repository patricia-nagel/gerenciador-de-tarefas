package com.quimera.taskmanager.dominio.tarefa.controller;

import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public void criarTarefa(@RequestBody TarefaRequestDto tarefaRequestDto) {
        //POST /tasks Criar uma nova tarefa.
        tarefaService.criarTarefa(tarefaRequestDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDto> buscarInformacoesTarefa(@PathVariable Long id) {
        //GET /tasks/{id} Obter detalhes de uma tarefa.
        return ResponseEntity.ok(tarefaService.buscarTarefa(id));
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDto>> buscarTarefasPorUsuario(@RequestParam Long assignedTo) {
        //GET /tasks?assignedTo={userId} Listar todas as tarefas atribuídas a um usuário.
        return ResponseEntity.ok(tarefaService.buscarTarefasPorUsuario(assignedTo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarInformacoesTarefa(@PathVariable Long id) {
        //PUT /tasks/{id} Atualizar informações da tarefa (título, descrição, status).
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deletarTarefa() {
        //DELETE /tasks/{id} Remover uma tarefa.
        return ResponseEntity.noContent().build();
    }
}
