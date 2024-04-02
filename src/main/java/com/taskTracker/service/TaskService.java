package com.taskTracker.service;

import com.taskTracker.dto.TaskDto;
import com.taskTracker.entity.Task;

import java.util.List;

public interface TaskService {
    Task addTask(TaskDto taskDto);
    boolean checkMandatoryFields(String name, String status);
    boolean checkIfDatesAreValid(String deadline);
    List<TaskDto> getAllTasks();
    TaskDto getTaskInfo(Long taskId);
    int getTasksNumber(int userId);
    int getFinishedTasksNumber(int userId);
    int getActiveTasksNumber(int userId);
    void setTaskStatus(Long id, String status);
    Task getTask(Long taskId);
}
