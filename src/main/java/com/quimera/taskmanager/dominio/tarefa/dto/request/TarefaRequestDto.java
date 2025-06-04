package com.quimera.taskmanager.dominio.tarefa.dto.request;

import com.quimera.taskmanager.dominio.tarefa.domain.Situacao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaRequestDto {

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 50, message = "O título pode ter no máximo 50 caracteres.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres.")
    private String descricao;

    @Schema(
            description = "Situações possíveis para uma tarefa",
            allowableValues = {"NOVO", "EM_ANDAMENTO", "BLOQUEADA", "FINALIZADA"}
    )
    private Situacao situacao;

    private Long idUsuario;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

}
