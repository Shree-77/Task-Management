package com.TaskManagement.demo.Controller;


import com.TaskManagement.demo.Model.Tasks;
import com.TaskManagement.demo.Repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tasks")
public class TasksController  {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

 @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<Tasks> addTask(@RequestBody Tasks task){
        try{
            Tasks savedTask = taskRepository.save(task);
            return ResponseEntity.ok(savedTask);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tasks> getTasks(){
        return taskRepository.findAll();
    }
}
