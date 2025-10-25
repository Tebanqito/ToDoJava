package com.toDo.crud.sevices;

import com.toDo.crud.dtos.ToDoDto;
import com.toDo.crud.dtos.ToDoResponseDto;
import com.toDo.crud.entities.ToDo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import com.toDo.crud.repositories.ToDoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Transactional(readOnly = true)
    public List<ToDo> getAllToDo(){
        return toDoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ToDoResponseDto> getToDoResponseById(Integer id) {
        return toDoRepository.findById(id).map(this::toResponse);
    }

    private static ToDo getToDo(ToDoDto toDoDto) {
        ToDo toDo = new ToDo();
        toDo.setName(toDoDto.getName());
        toDo.setDescription(toDoDto.getDescription());

        return toDo;
    }

    public ToDo createToDo(ToDoDto toDoDto) {
        ToDo toDo = getToDo(toDoDto);

        return toDoRepository.save(toDo);
    }

    private ToDoResponseDto toResponse(ToDo toDo) {
        return  new ToDoResponseDto(toDo.getId(), toDo.getName(), toDo.getDescription());
    }

    public ToDo updateToDo(ToDoDto toDoDto) throws EntityNotFoundException {
        ToDo toDo = toDoRepository.findById(toDoDto.getId()).orElseThrow(() -> new EntityNotFoundException("ToDo with ID: " + toDoDto.getId() + " not found"));
        toDo.setName(toDoDto.getName());
        toDo.setDescription(toDoDto.getDescription());

        return toDoRepository.save(toDo);
    }

    public void deleteToDo(Integer id) throws EntityNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException("The ToDo ID must not be null");
        }

        if(!toDoRepository.existsById(id)){
            throw new EntityNotFoundException("ToDo with ID: " + id + " not found");
        }

        toDoRepository.deleteById(id);
    }
}