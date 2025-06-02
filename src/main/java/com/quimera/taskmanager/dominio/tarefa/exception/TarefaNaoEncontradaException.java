package com.quimera.taskmanager.dominio.tarefa.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(Long id) {
        super("Tarefa com id " + id + " não foi encontrada");
    }
}
