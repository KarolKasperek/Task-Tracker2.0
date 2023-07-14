package com.taskTracker.controller;

import com.taskTracker.dto.TaskRequest;
import com.taskTracker.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class HomeControllerTest {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHome() {
        List<TaskRequest> taskList = new ArrayList<>();
        taskList.add(new TaskRequest());
        when(taskService.getAllTasks()).thenReturn(taskList);

        Model model = mock(Model.class);
        String viewName = homeController.getHome(model);

        assertEquals("index", viewName);
        verify(model, times(2)).addAttribute(anyString(), any());
        verify(model).addAttribute(eq("taskList"), eq(taskList));
    }

    @Test
    void testGetTaskRequest() {
        Model model = mock(Model.class);
        String viewName = homeController.getTaskRequest(model);

        assertEquals("index", viewName);
        verify(model).addAttribute(eq("taskRequest"), any(TaskRequest.class));
    }

    @Test
    void testProcessAddingTaskWithValidTaskRequest() {
        TaskRequest taskRequest = new TaskRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        String viewName = homeController.processAddingTask(taskRequest, bindingResult, model);

        assertEquals("redirect:/", viewName);
        verify(taskService).addTask(taskRequest);
        verify(model, never()).addAttribute(eq("fieldsNotFilledMsg"), anyString());
        verify(model, never()).addAttribute(eq("invalidDateMsg"), anyString());
    }

    @Test
    void testProcessAddingTaskWithInvalidTaskRequest() {
        TaskRequest taskRequest = new TaskRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        Model model = mock(Model.class);

        String viewName = homeController.processAddingTask(taskRequest, bindingResult, model);

        assertEquals("index", viewName);
        verify(taskService, never()).addTask(taskRequest);
        verify(model, never()).addAttribute(eq("fieldsNotFilledMsg"), anyString());
        verify(model, never()).addAttribute(eq("invalidDateMsg"), anyString());
    }

    @Test
    void testProcessAddingTaskWithIllegalArgumentException() {
        TaskRequest taskRequest = new TaskRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        String errorMsg = "Some error message";
        doThrow(new IllegalArgumentException(errorMsg)).when(taskService).addTask(taskRequest);

        String viewName = homeController.processAddingTask(taskRequest, bindingResult, model);

        assertEquals("index", viewName);
        verify(taskService).addTask(taskRequest);
        verify(model).addAttribute("fieldsNotFilledMsg", errorMsg);
        verify(model, never()).addAttribute(eq("invalidDateMsg"), anyString());
    }

    @Test
    void testProcessAddingTaskWithDateTimeException() {
        TaskRequest taskRequest = new TaskRequest();
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);

        String errorMsg = "Invalid date";
        doThrow(new DateTimeException(errorMsg)).when(taskService).addTask(taskRequest);

        String viewName = homeController.processAddingTask(taskRequest, bindingResult, model);

        assertEquals("index", viewName);
        verify(taskService).addTask(taskRequest);
        verify(model, never()).addAttribute(eq("fieldsNotFilledMsg"), anyString());
        verify(model).addAttribute("invalidDateMsg", errorMsg);
    }
}
