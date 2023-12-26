package org.sctp.todolist.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sctp.todolist.models.Task;
import org.sctp.todolist.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;
    @Mock
    TaskRepository taskRepository;

    private Task toDoApp, postVideo, buyLaptop, nodeJS;

    private List<Task> taskList = new ArrayList<>();


    @BeforeEach
    void addSomeTasksBeforeStartingTheTest() {
        toDoApp = new Task(true, "Complete To Do App");
        postVideo = new Task(true, "Post Videos on Programmers Zone");
        buyLaptop = new Task(false, "Buy a new laptop for myself");
        nodeJS = new Task(false, "Complete NodeJS Bootcamp");
        taskList = List.of(toDoApp, postVideo, buyLaptop, nodeJS);
    }

    @Test
    void shouldReturnAllTask() {

        // putting taskList to a mock taskRepository
        when(taskRepository.findAll()).thenReturn(taskList);

        // get list of tasks from taskRepository using TaskService
        List<Task> getAllTaskListFromTaskRepository = taskService.getAllTask();

        // test size of
        assertEquals(4, getAllTaskListFromTaskRepository.size());
    }

    @Test
    void shouldUpdateATaskProperly() {

        // putting toDoApp to a mock taskRepository
        when(taskRepository.save(any(Task.class))).thenReturn(toDoApp);
        toDoApp.setTaskDescription("Assessment Application");
        Task thisTask = taskService.updateTask(toDoApp);

        // test if updated task is
        assertNotNull(thisTask);
        // test if updating taskDescription is working
        assertEquals("Assessment Application", thisTask.getTaskDescription());
        // test that updating taskDescription will not affect isCompleted()
        assertTrue(thisTask.isCompleted());

        thisTask.setCompleted(false);
        assertFalse(thisTask.isCompleted());
    }
}