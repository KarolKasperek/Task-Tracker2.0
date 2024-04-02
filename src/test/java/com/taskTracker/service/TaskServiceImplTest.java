package com.taskTracker.service;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Task;
import com.taskTracker.mapper.TaskMapper;
import com.taskTracker.repo.TaskRepository;
import com.taskTracker.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;
    @InjectMocks
    private TaskServiceImpl taskServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("taskDto is added to repo")
    public void addTask() {

        //given
        TaskDto taskDto = new TaskDto();
        taskDto.setId(1L);
        taskDto.setName("create new branches");
        taskDto.setStatus("done");
        taskDto.setDeadline(LocalDate.of(2030, 11, 12));

        Task task = new Task();
        task.setId(1L);
        task.setName("create new branches");
        task.setStatus("done");
        task.setDeadline(LocalDate.of(2030, 11, 12));

        //when
        when(taskMapper.toTaskEntity(taskDto)).thenReturn(task);
        Task savedTask = taskServiceImpl.addTask(taskDto);

        //then
        verify(taskRepository, times(1)).save(task);
        assertThat(savedTask, equalTo(task));
    }

    @Test
    @DisplayName("method that checks mandatory fields is working correctly")
    public void checkMandatoryFields() {

        //given
        String name = "create new branches";
        String status = "done";

        //when
        //then
        assertThat(taskServiceImpl.checkMandatoryFields(name, status), equalTo(false));
    }

    @Test
    @DisplayName("method that checks if dates are valid is working correctly")
    public void checkIfDatesAreValid() {

        //given
        LocalDate localDate = LocalDate.of(2030, 11, 11);
        String deadLineString = localDate.toString();
        //when
        boolean checkResult = taskServiceImpl.checkIfDatesAreValid(deadLineString);

        //then
        assertThat(checkResult, equalTo(true));
    }

    @Test
    @DisplayName("getting task list")
    public void getAllTasks() {

        //given
        TaskDto taskDto1 = new TaskDto();
        taskDto1.setId(1L);
        taskDto1.setName("name1");
        taskDto1.setStatus("done");
        taskDto1.setDeadline(LocalDate.of(2030, 11, 11));
        TaskDto taskDto2 = new TaskDto();
        taskDto2.setId(2L);
        taskDto2.setName("name2");
        taskDto2.setStatus("done");
        taskDto2.setDeadline(LocalDate.of(2030, 11, 11));
        TaskDto taskDto3 = new TaskDto();
        taskDto3.setId(3L);
        taskDto3.setName("name3");
        taskDto3.setStatus("done");
        taskDto3.setDeadline(LocalDate.of(2030, 11, 11));

        Task task1 = new Task();
        task1.setId(1L);
        task1.setName("name1");
        task1.setStatus("done");
        task1.setStartDate(LocalDate.of(2025, 11, 11));
        task1.setDeadline(LocalDate.of(2030, 11, 11));
        Task task2 = new Task();
        task2.setId(2L);
        task2.setName("name2");
        task2.setStatus("done");
        task2.setStartDate(LocalDate.of(2025, 11, 11));
        task2.setDeadline(LocalDate.of(2030, 11, 11));
        Task task3 = new Task();
        task3.setId(3L);
        task3.setName("name3");
        task3.setStatus("done");
        task3.setStartDate(LocalDate.of(2025, 11, 11));
        task3.setDeadline(LocalDate.of(2030, 11, 11));

        List<TaskDto> taskDtoList = new LinkedList<>(List.of(taskDto1, taskDto2, taskDto3));
        List<Task> taskList = new LinkedList<>(List.of(task1, task2, task3));

        //when
        taskServiceImpl.addTask(taskDto1);
        taskServiceImpl.addTask(taskDto2);
        taskServiceImpl.addTask(taskDto3);
        when(taskRepository.findAll()).thenReturn(taskList);
        when(taskMapper.toTaskRequest(task1)).thenReturn(taskDto1);
        when(taskMapper.toTaskRequest(task2)).thenReturn(taskDto2);
        when(taskMapper.toTaskRequest(task3)).thenReturn(taskDto3);

        //then
        System.out.println(taskServiceImpl.getAllTasks().size());
        System.out.println(taskDtoList.size());
        assertThat(taskServiceImpl.getAllTasks().size(), equalTo(taskDtoList.size()));
        assertThat(taskServiceImpl.getAllTasks().get(1).getId(), equalTo(taskDtoList.get(1).getId()));
    }
}
