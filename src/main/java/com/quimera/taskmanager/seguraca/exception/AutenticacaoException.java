package com.quimera.taskmanager.seguraca.exception;

public class AutenticacaoException extends RuntimeException {
    public AutenticacaoException() {
        super("Usuário e/ou senha incorretos.");
    }
}
