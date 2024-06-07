package br.com.todo.model;

import lombok.Data;

@Data
public class TarefaVO {
    private Long codigo;
    private String descricao;
    private boolean completo;
}