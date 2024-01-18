package org.sctp.todolist.controllers;

import jakarta.validation.Valid;
import org.sctp.todolist.models.Task;
import org.sctp.todolist.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addSomeTasks")
    public ResponseEntity<List<Task>> addSomeTasks(@Valid @RequestBody List<Task> taskList) {
        return ResponseEntity.ok(taskService.addAll(taskList));
    }

    @GetMapping("/")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTask());
    }

    @GetMapping
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to my To-Do app!");
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        return ResponseEntity.ok(taskService.findAllCompletedTask());
    }

    @GetMapping("/incomplete")
    public ResponseEntity<List<Task>> getIncompleteTasks() {
        return ResponseEntity.ok(taskService.findAllIncompleteTask());
    }

    @PostMapping("/")
    public ResponseEntity<Object> createTask(@Valid @RequestBody Task task, Errors errors) {
        if (errors.hasErrors()) {
            var errorList = errors.getAllErrors();
            StringBuilder errorMessages = new StringBuilder();
            for (var e : errorList) {
                errorMessages.append(e.getClass().getSimpleName()).append(" - ").append(e.getDefaultMessage()).append("\n");
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok(taskService.createNewTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTask(@PathVariable Integer id, @Valid @RequestBody Task task, Errors errors) {

        Optional<Task> taskOrNull = taskService.findTaskById(id);
        // First check if id is not found
        if (taskOrNull.isEmpty()) {
            return ResponseEntity.badRequest().body("Task of id " + id + " not found.");
        } else {
            // If id is found, validate the task description has 2 or more characters
            if (errors.hasErrors()) {
                List<ObjectError> errorList = errors.getAllErrors();
                StringBuilder errorMessages = new StringBuilder();
                for (ObjectError e : errorList) {
                    errorMessages.append(e.getClass().getSimpleName()).append(" - ").append(e.getDefaultMessage()).append("\n");
                }
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Task thisTask = taskOrNull.get();
            return ResponseEntity.ok((taskService.updateTask(thisTask)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable Integer id) {
        Optional<Task> task = taskService.findTaskById(id);
        if (task.isPresent()) {
            Task deleteThisTask = task.get();
            taskService.deleteTask(deleteThisTask);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
