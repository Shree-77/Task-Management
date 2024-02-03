package com.TaskManagement.demo.Controller;


import com.TaskManagement.demo.Model.Project;
import com.TaskManagement.demo.Model.Tasks;
import com.TaskManagement.demo.Repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/project")

public class ProjectController {
    private final ProjectRepository projectRepository ;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping(path = "/add-project")
    public @ResponseBody ResponseEntity<Project> addProject(@RequestBody Project project) {
        try {
            Project savedProject = projectRepository.save(project);
            return ResponseEntity.ok(savedProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping(path = "/projects")
    public @ResponseBody Iterable<Project> getTasks(){
        return projectRepository.findAll();
    }
    @DeleteMapping(path = "/delete-project/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectID) {
        try {
            if (projectRepository.existsById(projectID)) {
                projectRepository.deleteById(projectID);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping(path = "/update-project/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Project updatedProject) {
        try {
            Optional<Project> existingProjectOptional = projectRepository.findById(projectId);

            if (existingProjectOptional.isPresent()) {
                Project existingProject = existingProjectOptional.get();
                updatedProject.setProjectId(projectId);
                existingProject.setTitle(updatedProject.getTitle());
                List<Tasks> updatedTasks = updatedProject.getTasks();
                existingProject.setTasks(updatedTasks);
                Project savedProject = projectRepository.save(existingProject);
                return ResponseEntity.ok(savedProject);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
