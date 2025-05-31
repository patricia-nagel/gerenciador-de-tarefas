package com.quimera.taskmanager.seguraca.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @PostMapping("/login")
    public void realizarLogin() {
        //POST /auth/login Login de usuários, retornando um token (por exemplo: JWT) para → autenticação nas demais requisições.
    }

    @PostMapping("/logout")
    public void realizarLogout() {
        //POST /auth/logout Logout do usuário.
    }
}
