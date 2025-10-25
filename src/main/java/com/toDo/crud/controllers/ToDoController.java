package com.toDo.crud.controllers;

import com.toDo.crud.dtos.ToDoDto;
import com.toDo.crud.dtos.ToDoResponseDto;
import com.toDo.crud.entities.ToDo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.toDo.crud.repositories.ToDoRepository;
import com.toDo.crud.sevices.ToDoService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private ToDoRepository toDoRepository;
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createToDo(@RequestBody ToDoDto toDoDto){
        ToDo created = toDoService.createToDo(toDoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<ToDo> getToDoS(){
        return toDoService.getAllToDo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> getProductById(@PathVariable Integer id) {
        return toDoService.getToDoResponseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody ToDoDto toDoDto){
        ToDo updated = toDoService.updateToDo(toDoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();
    }
}
