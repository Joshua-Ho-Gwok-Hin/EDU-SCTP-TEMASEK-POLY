package org.sctp.todolist.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sctp.todolist.models.Task;
import org.sctp.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private TaskService taskService;

    private Task toDoApp, postVideo, buyLaptop, nodeJS;
    private Task updatedTask1;


    @BeforeEach
    void setUp() {
        toDoApp = new Task(true, "Complete To Do App");
        postVideo = new Task(true, "Post Videos on Programmers Zone");
        buyLaptop = new Task(false, "Buy a new laptop for myself");
        nodeJS = new Task(false, "Complete NodeJS Bootcamp");
        updatedTask1 = new Task(false, "Buy groceries");
        updatedTask1.setId(1);
    }

    @Test
    void getCompletedTasks() throws Exception {
        List<Task> taskList = List.of(toDoApp, postVideo);
        when(taskService.findAllCompletedTask()).thenReturn(taskList);

        this.mockMvc.perform(get("http://localhost:8080/api/v1/tasks/completed")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(taskList.size())));
    }

    @Test
    void createTask() throws Exception {
        when(taskService.createNewTask(any(Task.class))).thenReturn(buyLaptop);
        this.mockMvc.perform(post("http://localhost:8080/api/v1/tasks/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buyLaptop)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(buyLaptop.getId())))
                .andExpect(jsonPath("$.completed", is(buyLaptop.isCompleted())))
                .andExpect(jsonPath("$.taskDescription", is(buyLaptop.getTaskDescription())));
    }

    @Test
    void updateTask() throws Exception {
        when(taskService.findTaskById(anyInt())).thenReturn(Optional.ofNullable(updatedTask1));
        when(taskService.updateTask(any(Task.class))).thenReturn(updatedTask1);
        this.mockMvc.perform(put("http://localhost:8080/api/v1/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTask1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(updatedTask1.getId())))
                .andExpect(jsonPath("$.completed", is(updatedTask1.isCompleted())))
                .andExpect(jsonPath("$.taskDescription", is(updatedTask1.getTaskDescription())));
    }

    @Test
    void deleteTask() throws Exception {
        when(taskService.findTaskById(anyInt())).thenReturn(Optional.of(nodeJS));
        doNothing().when(taskService).deleteTask(nodeJS);
        this.mockMvc.perform(delete("http://localhost:8080/api/v1/tasks/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}