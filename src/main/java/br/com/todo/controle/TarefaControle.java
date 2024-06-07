package br.com.todo.controle;

import br.com.todo.model.TarefaVO;
import br.com.todo.servico.TarefaServico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TarefaControle {

    private final TarefaServico tarefaServico;

    public TarefaControle(TarefaServico tarefaServico) {
        this.tarefaServico = tarefaServico;
    }

    @GetMapping("/")
    public String paginaInicial(Model modelo) {
        modelo.addAttribute("tarefas", tarefaServico.getListaTarefas());
        return "index";
    }

    @GetMapping("/novaTarefa")
    public String novaTarefa(Model modelo) {
        TarefaVO tarefa = new TarefaVO();
        modelo.addAttribute("tarefa", tarefa);
        return "novaTarefa";
    }

    @PostMapping("/salvarTarefa")
    public String salvarTarefa(@ModelAttribute("tarefa") TarefaVO tarefa) {
        tarefaServico.salvarTarefa(tarefa);
        return "redirect:/";
    }

    @GetMapping("/atualizarTarefa/{codigo}")
    public String atualizarTarefa(@PathVariable(value = "codigo") long codigo, Model modelo) {
        TarefaVO tarefa = tarefaServico.getTarefaPorCodigo(codigo).orElseThrow(() -> new IllegalArgumentException("Código de Tarefa Inválido: " + codigo));
        modelo.addAttribute("tarefa", tarefa);
        return "atualizarTarefa";
    }

    @GetMapping("/deletarTarefa/{codigo}")
    public String deletarTarefa(@PathVariable(value = "codigo") long codigo) {
        tarefaServico.deletarTarefa(codigo);
        return "redirect:/";
    }
}