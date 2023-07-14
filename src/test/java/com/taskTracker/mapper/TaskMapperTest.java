package com.taskTracker.mapper;

import com.taskTracker.dto.TaskRequest;
import com.taskTracker.entity.Task;
import com.taskTracker.exception.TaskDoesNotExistException;
import com.taskTracker.repo.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskMapperTest {

    @Mock
    private AccountRepository accountRepository;

    private TaskMapper taskMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        taskMapper = new TaskMapper(accountRepository);
    }

    @Test
    void toTaskEntity_WithValidTaskRequest_ReturnsTaskEntity() {
        // given
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setId(1L);
        taskRequest.setName("Task 1");
        taskRequest.setDescription("Description");
        taskRequest.setStatus("In Progress");
        taskRequest.setDeadline(LocalDate.now().plusDays(7));
        taskRequest.setStartDate(LocalDate.now());

        when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        // when
        Task task = taskMapper.toTaskEntity(taskRequest);

        // then
        assertNotNull(task);
        assertEquals(taskRequest.getId(), task.getId());
        assertEquals(taskRequest.getName(), task.getName());
        assertEquals(taskRequest.getDescription(), task.getDescription());
        assertEquals(taskRequest.getStatus(), task.getStatus());
        assertEquals(taskRequest.getDeadline(), task.getDeadline());
        assertEquals(taskRequest.getStartDate(), task.getStartDate());
    }

    @Test
    void toTaskEntity_WithNullTaskRequest_ThrowsTaskDoesNotExistException() {
        // Arrange
        TaskRequest taskRequest = null;

        // Act & Assert
        assertThrows(TaskDoesNotExistException.class, () -> taskMapper.toTaskEntity(taskRequest));
    }

    @Test
    void toTaskRequest_WithValidTask_ReturnsTaskRequest() {
        // Arrange
        Task task = new Task();
        task.setId(1L);
        task.setName("Task 1");
        task.setDescription("Description");
        task.setStatus("In Progress");
        task.setDeadline(LocalDate.now().plusDays(7));
        task.setStartDate(LocalDate.now());

        when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(null)); // Możesz dostosować to zachowanie do potrzeb testu

        // Act
        TaskRequest taskRequest = taskMapper.toTaskRequest(task);

        // Assert
        assertNotNull(taskRequest);
        assertEquals(task.getId(), taskRequest.getId());
        assertEquals(task.getName(), taskRequest.getName());
        assertEquals(task.getDescription(), taskRequest.getDescription());
        assertEquals(task.getStatus(), taskRequest.getStatus());
        assertEquals(task.getDeadline(), taskRequest.getDeadline());
        assertEquals(task.getStartDate(), taskRequest.getStartDate());
        assertNull(taskRequest.getAccountId());

        verify(accountRepository, times(0)).findById(anyLong()); // W tym teście nie wywołujemy findById, więc sprawdzamy, czy nie zostało wywołane
    }

    @Test
    void toTaskRequest_WithNullTask_ThrowsTaskDoesNotExistException() {
        // Arrange
        Task task = null;

        // Act & Assert
        assertThrows(TaskDoesNotExistException.class, () -> taskMapper.toTaskRequest(task));
    }
}
