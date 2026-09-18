package br.com.gerenciador_tarefas.service;

import br.com.gerenciador_tarefas.dto.TarefaRequest;
import br.com.gerenciador_tarefas.dto.TarefaRequestUpdate;
import br.com.gerenciador_tarefas.dto.TarefaResponse;
import br.com.gerenciador_tarefas.entity.Tarefa;
import br.com.gerenciador_tarefas.entity.TarefaStatus;
import br.com.gerenciador_tarefas.mapper.TarefaMapper;
import br.com.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço resposável pelas regras de negócio relacionados ao gerenciamento de tarefas
 */
@Service
public class TarefaService {

    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    public TarefaService(TarefaRepository repository, TarefaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Retorna todas tarefas cadastradas
     * @return Lista de DTOs {@link TarefaResponse}
     * representando as tarefas encontradas. Lista vazia caso nenhuma tarefa seja encontrada
     */

    public List<TarefaResponse> listar() {

        List<Tarefa> tarefas = repository.listarTodos();

        return mapper.toResponseList(tarefas);
    }

    /**
     * Retorna todas tarefas cadastradas concluidas
     * @return Lista de DTOs {@link TarefaResponse}
     * representando as tarefas concluidas encontradas. Lista vazia caso nenhuma tarefa seja encontrada
     */

    public List<TarefaResponse> listarConcluidos(){

        List<Tarefa> tarefasConcluidas = repository.listarConcluidos();

        return mapper.toResponseList(tarefasConcluidas);
    }

    /**
     * Retorna todas tarefas cadastradas pendentes
     * @return Lista de DTOs {@link TarefaResponse}
     * representando as tarefas pendentes encontradas. Lista vazia caso nenhuma tarefa seja encontrada
     */

    public List<TarefaResponse> listarPendentes(){

        List<Tarefa> tarefasPendentes = repository.listarPendentes();

        return mapper.toResponseList(tarefasPendentes);
    }

    /**
     * Busca uma tarefa pelo seu Identificador Único
     * @param id Identificador da tarefa a ser localizada
     * @return DTO {@link TarefaResponse} representando a tarefa encontrada
     * @throws IllegalArgumentException Se nenhuma tarefa for encontrada com o ID informado
     */

    public TarefaResponse buscarPorId(Long id) {

        return repository.buscarPorId(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada para o id: " + id));
    }


    /**
     * Cadastra uma nova tarefa na lista de dados.
     * @param request Objeto contendo os dados de entrada para criação da tarefa
     * @return DTO {@link TarefaResponse} com os dados da tarefa persistido
     */

    public TarefaResponse cadastrar(TarefaRequest request) {
        Tarefa tarefa = mapper.toEntity(request);
        tarefa.setPrioridade(TarefaStatus.PENDENTE);
        Tarefa salva = repository.inserir(tarefa);
        return mapper.toResponse(salva);
    }

    /**
     * Atualiza todos os dados de uma tarefa existente
     *
     * @param id Identificador da Tarefa a ser atualizada
     * @param request DTO com os novos dados da tarefa
     * @return DTO {@link TarefaResponse} com os dados da tarefa atualzados
     * @throws IllegalArgumentException Se nenhuma tarefa for encontrada com o ID informado
     */

    public TarefaResponse atualizar(Long id, TarefaRequestUpdate request) {
        Tarefa tarefa = repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada para o id: " + id));

        mapper.updateEntity(request, tarefa);
        tarefa.setPrioridade(TarefaStatus.PENDENTE);
        Tarefa atualizada = repository.inserir(tarefa);
        return mapper.toResponse(atualizada);
    }

    /**
     * Atualiza somente o status da tarefa para CONCLUIDO
     * @param id Identificador Único da tarefa a ser CONCLUIDO
     * @return Uma Entidade Tarefa CONCLUIDA
     */

    public TarefaResponse atualizarParcialmente(Long id) {
        Tarefa tarefa = repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada para o id: " + id));

        tarefa.setPrioridade(TarefaStatus.CONCLUIDO);
        Tarefa atualizada = repository.inserir(tarefa);
        return mapper.toResponse(atualizada);
    }

    /**
     * Remove a Entidade Tarefa de acordo com o Identificador Único
     * @param id Identificador Único da Entidade Tarefa
     * @throws IllegalArgumentException Se nenhuma tarefa for encontrada com o ID informado
     */

    public void remover(Long id) {
        Tarefa tarefa = repository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada para o id: " + id));

        repository.deletar(id);
    }
}
