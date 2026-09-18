package br.com.gerenciador_tarefas.mapper;

import br.com.gerenciador_tarefas.dto.TarefaRequest;
import br.com.gerenciador_tarefas.dto.TarefaRequestUpdate;
import br.com.gerenciador_tarefas.dto.TarefaResponse;
import br.com.gerenciador_tarefas.entity.Tarefa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TarefaMapper {

    /**
     * Converte os dados de criação para uma entidade Tarefa.
     * @param request dados recebidos para criação
     * @return entidade Tarefa
     */
    public Tarefa toEntity(TarefaRequest request){
        return Tarefa.builder()
                .titulo(request.titulo())
                .descricao(request.descricao())
                .build();
    }

    /**
     * Converte uma entidade Tarefa para o DTO de resposta
     * @param tarefa entidade persistida
     * @return representação pública da tarefa
     */
    public TarefaResponse toResponse(Tarefa tarefa){
        return new TarefaResponse(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getPrioridade());
    }

    /**
     * Converte uma lista de Entidades para uma lista de DTOs de resposta
     * @param tarefas lista de Entidades Tarefas
     * @return representação publica da Lista de Tarefas
     */
    public List<TarefaResponse> toResponseList(List<Tarefa> tarefas){
        return tarefas.stream().map(this::toResponse).toList();
    }

    public void updateEntity(TarefaRequestUpdate requestUpdate, Tarefa tarefa){
        tarefa.setTitulo(requestUpdate.titulo());
        tarefa.setDescricao(requestUpdate.descricao());
        tarefa.setPrioridade(requestUpdate.prioridade());
    }
}
