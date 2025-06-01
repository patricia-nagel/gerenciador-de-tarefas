package com.quimera.taskmanager.dominio.tarefa.controller;

import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponseDto> criarTarefa(@RequestBody TarefaRequestDto tarefaRequestDto) {
        //POST /tasks Criar uma nova tarefa.
        TarefaResponseDto tarefa = tarefaService.criarTarefa(tarefaRequestDto);
        URI location = URI.create("/tasks/" + tarefa.getTitulo()); //Mudar para Id
        return ResponseEntity.created(location).body(tarefa);
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
    public ResponseEntity<Void> atualizarInformacoesTarefa(@PathVariable Long id, @RequestBody TarefaRequestDto tarefaRequestDto) {
        //PUT /tasks/{id} Atualizar informações da tarefa (título, descrição, status).
        tarefaService.atualizarTarefa(id, tarefaRequestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) {
        //DELETE /tasks/{id} Remover uma tarefa.
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
