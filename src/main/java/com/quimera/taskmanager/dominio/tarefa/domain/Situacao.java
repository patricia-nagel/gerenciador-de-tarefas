package com.quimera.taskmanager.dominio.tarefa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Situacao {

    NOVO("1"),
    EM_ANDAMENTO("2"),
    BLOQUEADA("3"),
    FINALIZADA("4");

    private final String codigoSituacao;

    public static Situacao fromCodigo(String codigo) {
        return Arrays.stream(Situacao.values())
                .filter(s -> codigo.equals(s.getCodigoSituacao()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Código de situação inválido: " + codigo));
    }

}
