package br.com.gerenciador_tarefas.dto;

import br.com.gerenciador_tarefas.entity.TarefaStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Dados utilizados para completamente a tarefa
 *
 * @param titulo novo titulo da tarefa
 * @param descricao nova descrição da tarefa
 * @param prioridade nova prioridade da tarefa
 * */
@Schema(description = "Dados para atualização da tarefa")

public record TarefaRequestUpdate(

        @NotNull(message = "titulo deve ser obrigatorio")
        @Schema(example = "Tarefa SA BackEnd")
        String titulo,

        @NotNull(message = "descricao deve ser obrigatorio")
        @Schema(example = "Atualizações no GitProjects")
        String descricao,

        @Schema(example = "CONCLUIDA")
        TarefaStatus prioridade
) {}
