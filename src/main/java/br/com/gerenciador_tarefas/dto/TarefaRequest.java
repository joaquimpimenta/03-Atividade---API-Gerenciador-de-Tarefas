package br.com.gerenciador_tarefas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Dados necessarios para cadastrar uma nova tarefa
 *
 * @param titulo titulo da tarefa
 * @param descricao descricao da tarefa
 *
 */
@Schema(description = "Dados utilizados para cadastrar uma tarefa.")


public record TarefaRequest(
        @Schema(
                description = "Título da tarefa",
                example = "Fazendo atividades de APIREST"
        )
        @NotNull(message = "titulo deve ser obrigatorio")
        String titulo,

        @Schema(
                description = "Descrição da tarefa",
                example = "10 atividades sobre APIREST, para fazer hoje e entregar hoje."
        )
        @NotNull(message = "descricao deve ser obrigatorio")
        String descricao

) {}
