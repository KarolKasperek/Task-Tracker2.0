package com.taskTracker.mapper;

import com.taskTracker.dto.TaskRequest;
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

    public Task toTaskEntity(TaskRequest taskRequest) {

        if (taskRequest == null) {
            throw new TaskDoesNotExistException("Task does not exist!");
        }

        Task task = new Task();

        if (taskRequest.getStartDate() == null) {
            taskRequest.setStartDate(LocalDate.now());
        }

        if (taskRequest.getId() != null) {
            task.setId(taskRequest.getId());
        }

        task.setDeadline(taskRequest.getDeadline());
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.setDeadline(taskRequest.getDeadline());
        task.setStartDate(taskRequest.getStartDate());
        if (taskRequest.getAccountId() == null) {
            return task;
        }
        task.setAccount(accountRepo.findById(taskRequest.getAccountId()).orElseThrow()); //TODO obsluga tego wyjatku
        return task;
    }

    public TaskRequest toTaskRequest(Task task) {

        if (task == null) {
            throw new TaskDoesNotExistException("Task does not exist!");
        }

        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setId(task.getId());
        taskRequest.setDeadline(task.getDeadline());
        taskRequest.setName(task.getName());
        taskRequest.setDescription(task.getDescription());
        taskRequest.setStatus(task.getStatus());
        taskRequest.setDeadline(task.getDeadline());
        taskRequest.setStartDate(task.getStartDate());
        if (task.getAccount() != null) {
            taskRequest.setAccountId(task.getAccount().getId());
        }

        return taskRequest;
    }
}
