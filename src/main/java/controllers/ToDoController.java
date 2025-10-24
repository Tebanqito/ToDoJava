package controllers;

import entities.ToDo;
import org.springframework.web.bind.annotation.*;
import repositories.ToDoRepository;
import sevices.ToDoService;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private ToDoRepository toDoRepository;
    private ToDoService toDoService;

    //@GetMapping()
    //public String index(){
      //  return "CONECTED";
    //}

    @GetMapping("/todos")
    public List<ToDo> getToDoS(){
        return toDoService.getAllToDo();
    }

    @PostMapping("create")
    public String save(@RequestBody ToDo todo){
        toDoRepository.save(todo);
        return "ToDo saved";
    }

    @PutMapping("edit/{id}")
    public String update(@PathVariable Long id, @RequestBody ToDo toDo){
        ToDo updateToDo = toDoRepository.findById(id).get();
        updateToDo.setName(toDo.getName());
        updateToDo.setDescription(toDo.getDescription());
        toDoRepository.save(updateToDo);
        return "ToDo updated correctly";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        ToDo deleteToDo = toDoRepository.findById(id).get();
        toDoRepository.delete(deleteToDo);
        return "ToDo deleted correctly";
    }
}
