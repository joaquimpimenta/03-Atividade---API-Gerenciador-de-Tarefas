package br.com.gerenciador_tarefas.entity;

import lombok.*;

/**
 * Representa uma tarefa persistida pela aplicação
 * <p>Está entidade contém os dados internos utilizados para camada
 * de persistência</p>
 */

@Builder
public class Tarefa {

    private Long id;
    private String titulo;
    private String descricao;
    private TarefaStatus prioridade;

    public Tarefa () {}

    public Tarefa(Long id, String titulo, String descricao, TarefaStatus prioridade) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public TarefaStatus getPrioridade() {
        return prioridade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPrioridade(TarefaStatus prioridade) {
        this.prioridade = prioridade;
    }
}
