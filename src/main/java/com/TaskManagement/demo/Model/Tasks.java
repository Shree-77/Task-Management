package com.TaskManagement.demo.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Document
public class Tasks {
    @Id
    private String taskId;
    @Field
    private String title;
    @Field
    private String description;
    @Field
    private LocalDate dueDate;
    @Field
    private TaskPriority priority;
    @Field
    private boolean completed;

    public Tasks() {
    }

    public Tasks(String title, String description, LocalDate dueDate, TaskPriority priority, boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format("Task[ taskid='%s',title = '%s' , description='%s', dueDate = '%s' ,priority = '%s', completed = '%s']",
                taskId,
                title,
                description,
                dueDate,
                priority,
                completed
        );
    }


}
