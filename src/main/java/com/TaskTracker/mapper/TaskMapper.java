package com.TaskTracker.mapper;

import com.TaskTracker.dto.TaskRequest;
import com.TaskTracker.entity.Task;
import com.TaskTracker.exception.TaskDoesNotExistException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TaskMapper {
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
        task.setUser(taskRequest.getUser());

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
        taskRequest.setUser(task.getUser());

        return taskRequest;
    }
}
