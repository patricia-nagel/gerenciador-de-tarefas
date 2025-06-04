package com.quimera.taskmanager.dominio.usuario.controller;

import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.service.UsuarioService;
import com.quimera.taskmanager.seguraca.configuracao.ConfiguracaoSeguranca;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "usuario", description = "Controlador para manipular dados de usuários")
@SecurityRequirement(name = ConfiguracaoSeguranca.SECURITY)
@Validated
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @PostMapping()
    @Operation(summary = "Criar um novo usuário", description = "Método para criar um novo usuário")
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso")
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        UsuarioResponseDto usuario = usuarioService.criarUsuario(usuarioRequestDto);
        URI location = URI.create("/users/" + usuario.getId());
        return ResponseEntity.created(location).body(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar informações de um usuário", description = "Método para buscar as informações um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioResponseDto> buscarInformacoesUsuario(@NotBlank @PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarUsuario(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar informações de um usuário", description = "Método para atualizar as informações um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> atualizarInformacoesUsuario(@NotBlank @PathVariable Long id, @Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        usuarioService.atualizarUsuario(id, usuarioRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um usuário", description = "Método para realizar soft delete de um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<Void> deletarUsuario(@NotBlank @PathVariable Long id) {
        usuarioService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
