package com.quimera.taskmanager.dominio.usuario.controller;

import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<UsuarioResponseDto> criarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        //POST /users Criar um novo usuário.
        UsuarioResponseDto usuario = usuarioService.criarUsuario(usuarioRequestDto);
        URI location = URI.create("/users/" + usuario.getId());
        return ResponseEntity.created(location).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarInformacoesUsuario(@PathVariable Long id) {
        //GET /users/{id} Obter informações de um usuário específico.
        return ResponseEntity.ok(usuarioService.buscarUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizarInformacoesUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        //PUT /users/{id} Atualizar informações do usuário
        usuarioService.atualizarUsuario(id, usuarioRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        //DELETE /users/{id} Remover um usuário (soft delete recomendado).
        usuarioService.softDelete(id);
        return ResponseEntity.noContent().build();
    }
}
