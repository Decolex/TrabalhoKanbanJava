package com.example.demo.repository;

import com.example.demo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepositorio extends JpaRepository<Tarefa, Long> {
}
