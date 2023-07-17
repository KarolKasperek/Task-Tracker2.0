package com.taskTracker.mapper;

import com.taskTracker.dto.TaskDto;
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
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setName("Task 1");
        taskDto.setDescription("Description");
        taskDto.setStatus("In Progress");
        taskDto.setDeadline(LocalDate.now().plusDays(7));
        taskDto.setStartDate(LocalDate.now());

        when(accountRepository.findById(1L)).thenReturn(Optional.ofNullable(null));

        // when
        Task task = taskMapper.toTaskEntity(taskDto);

        // then
        assertNotNull(task);
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getName(), task.getName());
        assertEquals(taskDto.getDescription(), task.getDescription());
        assertEquals(taskDto.getStatus(), task.getStatus());
        assertEquals(taskDto.getDeadline(), task.getDeadline());
        assertEquals(taskDto.getStartDate(), task.getStartDate());
    }

    @Test
    void toTaskEntity_WithNullTaskRequest_ThrowsTaskDoesNotExistException() {
        // Arrange
        TaskDto taskDto = null;

        // Act & Assert
        assertThrows(TaskDoesNotExistException.class, () -> taskMapper.toTaskEntity(taskDto));
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
        TaskDto taskDto = taskMapper.toTaskRequest(task);

        // Assert
        assertNotNull(taskDto);
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getName(), taskDto.getName());
        assertEquals(task.getDescription(), taskDto.getDescription());
        assertEquals(task.getStatus(), taskDto.getStatus());
        assertEquals(task.getDeadline(), taskDto.getDeadline());
        assertEquals(task.getStartDate(), taskDto.getStartDate());
        assertNull(taskDto.getAccountId());

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
