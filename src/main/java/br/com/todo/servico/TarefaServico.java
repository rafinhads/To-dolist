package br.com.todo.servico;

import br.com.todo.model.TarefaVO;
import br.com.todo.repositorio.TarefaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaServico {

    private final TarefaRepositorio tarefaRepository;

    public TarefaServico(TarefaRepositorio tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    public List<TarefaVO> getListaTarefas() {
        return tarefaRepository.buscarLista();
    }

    public void salvarTarefa(TarefaVO tarefa) {
        tarefaRepository.salvar(tarefa);
    }

    public Optional<TarefaVO> getTarefaPorCodigo(Long codigo) {
        return tarefaRepository.buscarPorCodigo(codigo);
    }

    public void deletarTarefa(Long codigo) {
        tarefaRepository.deletarPorCodigo(codigo);
    }
}