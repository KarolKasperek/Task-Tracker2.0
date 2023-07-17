package com.taskTracker.mapper;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Task;
import com.taskTracker.exception.TaskDoesNotExistException;
import com.taskTracker.repo.AccountRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {
    private AccountRepository accountRepo;

    public TaskMapper(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Task toTaskEntity(TaskDto taskDto) {

        if (taskDto == null) {
            throw new TaskDoesNotExistException("Task does not exist!");
        }

        Task task = new Task();

        if (taskDto.getStartDate() == null) {
            taskDto.setStartDate(LocalDate.now());
        }

        if (taskDto.getId() != null) {
            task.setId(taskDto.getId());
        }

        task.setDeadline(taskDto.getDeadline());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setDeadline(taskDto.getDeadline());
        task.setStartDate(taskDto.getStartDate());
        if (taskDto.getAccountId() == null) {
            return task;
        }
        task.setAccount(accountRepo.findById(taskDto.getAccountId()).orElseThrow()); //TODO obsluga tego wyjatku
        return task;
    }

    public TaskDto toTaskRequest(Task task) {

        if (task == null) {
            throw new TaskDoesNotExistException("Task does not exist!");
        }

        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setStartDate(task.getStartDate());
        if (task.getAccount() != null) {
            taskDto.setAccountId(task.getAccount().getId());
        }

        return taskDto;
    }
}
