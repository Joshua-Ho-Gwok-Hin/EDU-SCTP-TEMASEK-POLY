package org.sctp.todolist.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "task_table")
public class Task {
    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_completed")
    private boolean completed;

    // Validation of Task Description to have a minimum of 2 characters
    @NotNull(message = "Task description must not be null")
    @Size(min = 2, message = "Task description must have a min of 2 characters")
    @Column(name = "description")
    private String taskDescription;

    public Task() {
    }

    public Task(boolean completed, String taskDescription) {
        this.completed = completed;
        this.taskDescription = taskDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
}
