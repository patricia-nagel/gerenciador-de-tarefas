package com.quimera.taskmanager.dominio.usuario.controller;

import com.quimera.taskmanager.dominio.usuario.dto.request.UsuarioRequestDto;
import com.quimera.taskmanager.dominio.usuario.dto.response.UsuarioResponseDto;
import com.quimera.taskmanager.dominio.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public void criarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        //POST /users Criar um novo usuário.
        usuarioService.salvar(usuarioRequestDto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDto buscarInformacoesUsuario(@PathVariable Long id) {
        //GET /users/{id} Obter informações de um usuário específico.
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public void atualizarInformacoesUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto usuarioRequestDto) {
        //PUT /users/{id} Atualizar informações do usuário
        usuarioService.atualizar(id, usuarioRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        //DELETE /users/{id} Remover um usuário (soft delete recomendado).
        usuarioService.softDelete(id);
    }
}
