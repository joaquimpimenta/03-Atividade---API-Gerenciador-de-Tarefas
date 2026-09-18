package br.com.gerenciador_tarefas.repository;

import br.com.gerenciador_tarefas.entity.Tarefa;
import br.com.gerenciador_tarefas.entity.TarefaStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repositório responsável pelo acesso dos dados de Tarefas
 */

@Repository
public class TarefaRepository {

    private final AtomicLong sequencialId = new AtomicLong(1);
    private final List<Tarefa> tarefas = new ArrayList<>();
    private final List<Tarefa> filtradosPendentes = new ArrayList<>();
    private final List<Tarefa> filtradosConcluidos = new ArrayList<>();

    /**
     * Armazena todas as Entidades Tarefas Concluidas em uma lista
     * @return uma lista de Entidades Tarefas Concluidas.
     */

    public List<Tarefa> listarConcluidos(){
        for (Tarefa tarefa: tarefas){
            if (tarefa.getPrioridade().equals(TarefaStatus.CONCLUIDO)){
                filtradosConcluidos.add(tarefa);
                return new ArrayList<>(filtradosConcluidos);
            }
        }
        return listarConcluidos();
    }

    /**
     * Armazena todas as Entidades Tarefas Pendentes em uma lista
     * @return uma lista de Entidades Tarefas Pendentes.
     */

    public List<Tarefa> listarPendentes(){
        for (Tarefa tarefa: tarefas){
            if (tarefa.getPrioridade().equals(TarefaStatus.PENDENTE)){
                filtradosPendentes.add(tarefa);
                return new ArrayList<>(filtradosPendentes);
            }
        }
        return listarPendentes();
    }

    /**
     * Armazena todas as Entidades Tarefas em uma lista.
     * @return uma lista de Entidades Tarefas.
     */

    public List<Tarefa> listarTodos() {

        return new ArrayList<>(tarefas);
    }

    /**
     * Realiza a busca de acordo com o Identificador Único para encontrar a Entidade desejada
     * @param id Identificar Único da Entidade
     * @return uma Entidade Tarefa de acordo com o Identificador Único selecionado
     */
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefas.stream().
                filter(tarefa -> tarefa.getId().equals(id)).
                findFirst();
    }

    /**
     * Realiza a inserção da Entidade Tarefa no Lista
     * @param tarefa Entidade Tarefa
     * @return retorna a Tarefa criada
     */
    public Tarefa inserir(Tarefa tarefa) {
        if (tarefa.getId() == null) {
            tarefa.setId(sequencialId.getAndIncrement());
            tarefas.add(tarefa);
            return tarefa;
        }
        return tarefa;
    }

    /**
     * Realiza a exclusão da Entidade Tarefa na Lista de acordo com o Identificador único
     * @param id Identificar Único da Entidade
     * @return verifica se o Identificador único fornecido existe para realizar a exclusão na Lista da entidade
     */
    public boolean deletar(Long id) {
        return tarefas.removeIf(tarefa -> tarefa.getId().equals(id));
    }
}