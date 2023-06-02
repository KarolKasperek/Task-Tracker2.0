package com.TaskTracker.service;

import com.TaskTracker.dto.TaskRequest;
import com.TaskTracker.entity.Task;
import com.TaskTracker.repo.TaskRepository;
import lombok.AllArgsConstructor;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.stereotype.Service;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public void addTask(TaskRequest taskDTO) {
        if (checkMandatoryFields(taskDTO.getName(), taskDTO.getStatus())) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (!checkIfDatesAreValid(taskDTO.getDeadline().toString())) {
            throw new DateTimeException("Invalid date selected!");
        }
        Task taskEntity =
                new Task(
                        taskDTO.getName(),
                        taskDTO.getStatus(),
                        taskDTO.getDescription(),
                        taskDTO.getDeadline());
        taskRepository.save(taskEntity);
    }

    private boolean checkMandatoryFields(String name, String status) {
        return name.isBlank() || status.isBlank();
    }

    private boolean checkIfDatesAreValid(String deadline) {
        return deadline.isBlank() || LocalDate.now().isBefore(LocalDate.parse(deadline));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskInfo(Long taskId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        return taskOptional.orElseThrow(() -> new RuntimeException("task doesn't exist"));
    }
}
