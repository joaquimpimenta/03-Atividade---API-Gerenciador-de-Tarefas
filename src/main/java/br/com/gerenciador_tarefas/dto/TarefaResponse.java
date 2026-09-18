package br.com.gerenciador_tarefas.dto;

import br.com.gerenciador_tarefas.entity.TarefaStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Representação pública de uma tarefa retornado pela API
 *
 * @param id            identificação da tarefa
 * @param titulo        título da tarefa
 * @param descricao     descrição da tarefa
 * @param prioridade    prioridade da tarefa
 */
@Schema(description = "Dados de uma tarefa retornada pela API")

public record TarefaResponse(
        @Schema(
                description = "Identificador único da tarefa", example = "1"
        )
        Long id,

        @Schema(
                description = "Título da tarefa", example = "Estudar Java"
        )
        String titulo,

        @Schema(
                description = "Descrição da tarefa", example = "Rever conceitos de lambda."
        )
        String descricao,

        @Schema(
                description = "Prioridade da tarefa", example = "PENDENTE ou CONCLUIDA"
        )
        TarefaStatus prioridade
) {}
