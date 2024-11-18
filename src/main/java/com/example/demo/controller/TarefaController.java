package com.example.demo.controller;

import com.example.demo.model.Tarefa;
import com.example.demo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listar();
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.criar(tarefa);
        return ResponseEntity.ok(novaTarefa);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarStatus(@PathVariable Long id, @RequestParam String novoStatus) {
        Tarefa tarefaAtualizada = tarefaService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @PostMapping("/{id}/move")
    public ResponseEntity<Tarefa> moveTask(@PathVariable Long id) {
        Tarefa tarefaAtualizada = tarefaService.mover(id);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tarefaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
