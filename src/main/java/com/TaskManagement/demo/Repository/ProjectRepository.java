package com.TaskManagement.demo.Repository;

import com.TaskManagement.demo.Model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends MongoRepository<Project , String> {
}
