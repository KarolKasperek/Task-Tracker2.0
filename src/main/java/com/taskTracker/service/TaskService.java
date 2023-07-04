package com.taskTracker.service;

import com.taskTracker.dto.TaskRequest;
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

    public void addTask(TaskRequest taskRequest) {
        if (checkMandatoryFields(taskRequest.getName(), taskRequest.getStatus())) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (!checkIfDatesAreValid(taskRequest.getDeadline().toString())) {
            throw new DateTimeException("Invalid date selected!");
        }
        Task task = taskMapper.toTaskEntity(taskRequest);
        taskRepository.save(task);
    }

    private boolean checkMandatoryFields(String name, String status) {
        return name.isBlank() || status.isBlank();
    }

    private boolean checkIfDatesAreValid(String deadline) {
        return deadline.isBlank() || LocalDate.now().isBefore(LocalDate.parse(deadline));
    }

    public List<TaskRequest> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> taskMapper.toTaskRequest(task))
                .collect(Collectors.toList());
    }

    public Task getTaskInfo(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        return taskOptional.orElseThrow(() -> new RuntimeException("task doesn't exist"));
    }
}
