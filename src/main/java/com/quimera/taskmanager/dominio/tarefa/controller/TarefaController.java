package com.quimera.taskmanager.dominio.tarefa.controller;

import com.quimera.taskmanager.configuracao.SecurityConfigSwagger;
import com.quimera.taskmanager.dominio.tarefa.dto.request.TarefaRequestDto;
import com.quimera.taskmanager.dominio.tarefa.dto.response.TarefaResponseDto;
import com.quimera.taskmanager.dominio.tarefa.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "tarefa", description = "Controlador para manipular dados de tarefas")
@SecurityRequirement(name = SecurityConfigSwagger.SECURITY)
@Validated
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    @Operation(summary = "Criar uma nova tarefa", description = "Método para criar uma nova tarefa")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")
    public ResponseEntity<TarefaResponseDto> criarTarefa(@Valid @RequestBody TarefaRequestDto tarefaRequestDto) {
        TarefaResponseDto tarefa = tarefaService.criarTarefa(tarefaRequestDto);
        URI location = URI.create("/tasks/" + tarefa.getId());
        return ResponseEntity.created(location).body(tarefa);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar os detalhes de uma tarefa", description = "Método para buscar os detalhes de uma tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<TarefaResponseDto> buscarDetalhesTarefa(@NotBlank @PathVariable Long id) {
        return ResponseEntity.ok(tarefaService.buscarTarefa(id));
    }

    @GetMapping
    @Operation(summary = "Buscar a lista de tarefas atribuidas a um usuário", description = "Método para buscar a lista de tarefas atribuidas a um usuário")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas para o usuário")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<List<TarefaResponseDto>> buscarTarefasPorUsuario(@NotBlank @RequestParam Long assignedTo) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorUsuario(assignedTo));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar os detalhes de uma tarefa", description = "Método para atualizar os detalhes de uma tarefa")
    @ApiResponse(responseCode = "204", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> atualizarInformacoesTarefa(@NotBlank @PathVariable Long id, @Valid @RequestBody TarefaRequestDto tarefaRequestDto) {
        tarefaService.atualizarTarefa(id, tarefaRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar uma tarefa", description = "Método para deletar uma tarefa")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarTarefa(@NotBlank @PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
