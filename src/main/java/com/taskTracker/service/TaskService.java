package com.taskTracker.service;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Task;
import com.taskTracker.mapper.TaskMapper;
import com.taskTracker.repo.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;

    public void addTask(TaskDto taskDto) {
        if (checkMandatoryFields(taskDto.getName(), taskDto.getStatus())) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (!checkIfDatesAreValid(taskDto.getDeadline().toString())) {
            throw new DateTimeException("Invalid date selected!");
        }
        Task task = taskMapper.toTaskEntity(taskDto);
        taskRepository.save(task);
    }

    private boolean checkMandatoryFields(String name, String status) {
        return name.isBlank() || status.isBlank();
    }

    private boolean checkIfDatesAreValid(String deadline) {
        return deadline.isBlank() || LocalDate.now().isBefore(LocalDate.parse(deadline));
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> taskMapper.toTaskRequest(task))
                .collect(Collectors.toList());
    }

    public TaskDto getTaskInfo(Long taskId) {
        Optional<TaskDto> taskRequestOptional = Optional.ofNullable(taskMapper.toTaskRequest(taskRepository.findById(taskId).get()));
        return taskRequestOptional.orElseThrow(() -> new RuntimeException("task does not exist"));
    }

    public int getTasksNumber(int userId) {
        int tasksCounter = 0;

        for (Task task : taskRepository.findAll()) {
            if (task.getAccount() != null && task.getAccount().getId() == userId) {
                tasksCounter++;
            }
        }

        return tasksCounter;
    }

    public int getFinishedTasksNumber(int userId) {
        int finishedTasks = 0;

        for (Task task : taskRepository.findAll()) {
            if (task.getAccount() != null && task.getAccount().getId() == userId && task.getStatus().equals("done")) {
                finishedTasks++;
            }
        }

        return finishedTasks;
    }

    public int getActiveTasksNumber(int userId) {
        return getTasksNumber(userId)-getFinishedTasksNumber(userId);
    }
}
