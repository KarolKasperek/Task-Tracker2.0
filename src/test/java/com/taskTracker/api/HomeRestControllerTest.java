package com.taskTracker.api;

import com.taskTracker.dto.TaskRequest;
import com.taskTracker.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeRestControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private HomeRestController homeRestController;

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
    }

    @Test
    void getTasks_ReturnsListOfTasks() {
        // given
        List<TaskRequest> expectedTasks = new ArrayList<>();
        when(taskService.getAllTasks()).thenReturn(expectedTasks);

        // when
        List<TaskRequest> result = homeRestController.getTasks();

        // then
        assertEquals(expectedTasks, result);
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void addTask_ValidTask_ReturnsSuccessMessage() {
        // given
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("Test task");
        taskRequest.setDescription("Test description");

        // when
        String result = homeRestController.addTask(taskRequest);

        // then
        assertEquals("Task added successfully.", result);
        verify(taskService, times(1)).addTask(taskRequest);
    }

    @Test
    void addTask_ThrowsDateTimeException_ReturnsErrorMessage() {
        // given
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("Test task");
        taskRequest.setDescription("Test description");

        doThrow(new DateTimeException("Invalid date")).when(taskService).addTask(taskRequest);

        // when
        String result = homeRestController.addTask(taskRequest);

        // then
        assertEquals("Invalid date", result);
        verify(taskService, times(1)).addTask(taskRequest);
    }
}
