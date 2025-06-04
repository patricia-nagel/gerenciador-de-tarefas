package com.quimera.taskmanager.seguranca.exception;

public class AutenticacaoException extends RuntimeException {
    public AutenticacaoException() {
        super("Usuário e/ou senha incorretos.");
    }
}
