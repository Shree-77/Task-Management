package com.TaskManagement.demo.Controller;


import com.TaskManagement.demo.Model.Project;
import com.TaskManagement.demo.Model.Tasks;
import com.TaskManagement.demo.Repository.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "/project")

public class ProjectController {
    private final ProjectRepository projectRepository ;



    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;

    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable<Project> getProjects(){
        return projectRepository.findAll();
    }

    @DeleteMapping(path = "/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        try {
            if (projectRepository.existsById(projectId)) {
                projectRepository.deleteById(projectId);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(path = "/")
    public @ResponseBody ResponseEntity<Project> addProject(@RequestBody Project project) {
        try {
            Project savedProject = projectRepository.save(project);
            return ResponseEntity.ok(savedProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(path = "/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Project updatedProject) {
        try {
            // Retrieve the existing project from the database
            Optional<Project> existingProjectOptional = projectRepository.findById(projectId);

            if (existingProjectOptional.isPresent()) {
                // Get the existing project
                Project existingProject = existingProjectOptional.get();

                // Update the project's title if it's provided in the updated project
                if (updatedProject.getTitle() != null) {
                    existingProject.setTitle(updatedProject.getTitle());
                }

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
