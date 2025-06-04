package com.quimera.taskmanager.seguranca.controller;

import com.quimera.taskmanager.dominio.usuario.service.UsuarioService;
import com.quimera.taskmanager.seguranca.dto.LoginRequestDto;
import com.quimera.taskmanager.seguranca.dto.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> realizarLogin(@RequestBody LoginRequestDto loginUserDto) {
        LoginResponseDto token = usuarioService.autenticarUsuario(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void realizarLogout() {
        //POST /auth/logout Logout do usuário.
    }
}
