package com.TaskManagement.demo.Controller;


import com.TaskManagement.demo.Model.Project;
import com.TaskManagement.demo.Model.Tasks;
import com.TaskManagement.demo.Repository.ProjectRepository;
import com.TaskManagement.demo.Repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/project/{projectId}")
public class TasksController  {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;


    public TasksController(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<Tasks> addTask(@PathVariable String projectId , @RequestBody Tasks task){
        try{
            Optional<Project> project = projectRepository.findById(projectId);
            Tasks savedTask = null;
            if(project.isPresent()){
                Project ExistingProject = project.get();
                 savedTask = taskRepository.save(task);
                ExistingProject.getTasks().add(savedTask);
            }
            return ResponseEntity.ok(savedTask);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/tasks")
    public @ResponseBody Iterable<Tasks> getTasks(){
        return taskRepository.findAll();
    }

    @PutMapping(path = "/update-task/{taskId}")
    public ResponseEntity<Tasks> updateTask(@PathVariable String projectId, @PathVariable String taskId, @RequestBody Tasks updatedTask) {
        try {
            Optional<Tasks> existingTaskOptional = taskRepository.findById(taskId);
            if (existingTaskOptional.isPresent()) {
                Tasks existingTask = existingTaskOptional.get();
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

    @DeleteMapping(path = "/delete-task/{taskId}")
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
