package com.quimera.taskmanager.dominio.usuario.controller;

import com.quimera.taskmanager.dominio.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping()
    public void criarUsuario() {
        //POST /users Criar um novo usuário.
    }

    @GetMapping("/{id}")
    public void buscarInformacoesUsuario() {
        //GET /users/{id} Obter informações de um usuário específico.
    }

    @PutMapping("/{id}")
    public void atualizarInformacoesUsuario() {
        //PUT /users/{id} Atualizar informações do usuário
    }

    @DeleteMapping("/{id}")
    public void deletarUsuario() {
        //DELETE /users/{id} Remover um usuário (soft delete recomendado).
    }
}
