package com.toDo.crud.repositories;

import com.toDo.crud.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
