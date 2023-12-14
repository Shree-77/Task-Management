package com.TaskManagement.demo.Repository;


import com.TaskManagement.demo.Model.Tasks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository  extends MongoRepository<Tasks , String> {

}
