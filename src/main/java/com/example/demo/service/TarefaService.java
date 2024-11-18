package com.example.demo.service;

import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepositorio tarefaRepositorio;

    public List<Tarefa> listar() {
        return tarefaRepositorio.findAll();
    }

//------------------------------------------------ CRIAR TAREFA -----------------------------------------------\\

    public Tarefa criar(Tarefa tarefa) {
        tarefa.setData_criacao(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        tarefa.setStatus("A Fazer"); // Tarefas sempre começam na coluna "A Fazer"
        return tarefaRepositorio.save(tarefa);
    }

//------------------------------------------------ ATT TAREFA -------------------------------------------------\\


    public Tarefa atualizarStatus(Long id, String novoStatus) {
        Tarefa tarefa = tarefaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));
        tarefa.setStatus(novoStatus);
        return tarefaRepositorio.save(tarefa);
    }

//------------------------------------------------ MOVER TAREFA -----------------------------------------------\\


    public Tarefa mover(Long id) {
        Tarefa tarefa = tarefaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada."));

//----------------------------------------- TAREFA NA PROXIMA COLUNA ------------------------------------------\\

        switch (tarefa.getStatus()) {
            case "A Fazer":
                tarefa.setStatus("Em Progresso");
                break;
            case "Em Progresso":
                tarefa.setStatus("Concluído");
                break;
            case "Concluído":
                throw new RuntimeException("Tarefa já está concluída.");
            default:
                throw new RuntimeException("Status inválido.");
        }
        return tarefaRepositorio.save(tarefa);
    }

//----------------------------------------------- EXCLUIR TAREFA ----------------------------------------------\\


    public void excluir(Long id) {
        if (!tarefaRepositorio.existsById(id)) {
            throw new RuntimeException("Tarefa não encontrada.");
        }
        tarefaRepositorio.deleteById(id);
    }
}
