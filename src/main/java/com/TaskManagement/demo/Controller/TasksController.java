package com.TaskManagement.demo.Controller;


import com.TaskManagement.demo.Model.Tasks;
import com.TaskManagement.demo.Repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @PutMapping(path = "/update/{taskId}")
    public ResponseEntity<Tasks> updateTask(@PathVariable String taskId, @RequestBody Tasks updatedTask) {
        try {
            System.out.println("Received taskId: " + taskId);
            System.out.println("Received updatedTask: " + updatedTask);
            Optional<Tasks> existingTaskOptional = taskRepository.findById(taskId);

            if (existingTaskOptional.isPresent()) {
                Tasks existingTask = existingTaskOptional.get();
                updatedTask.setTaskId(taskId);
                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setDueDate(updatedTask.getDueDate());
                existingTask.setPriority(updatedTask.getPriority());
                existingTask.setStatus(updatedTask.getStatus());
                Tasks savedTask = taskRepository.save(existingTask);

                return ResponseEntity.ok(savedTask);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @DeleteMapping(path = "/delete/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId) {
        try {
            if (taskRepository.existsById(taskId)) {
                taskRepository.deleteById(taskId);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
