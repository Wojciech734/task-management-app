package com.example.taskManagementApp.task;

import com.example.taskManagementApp.user.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    Task createNewTask(Task task, Long userId) throws Exception;
    Task editTask(Task newTask, Long taskId) throws Exception;
    String deleteTask(Long id);
    List<Task> findAllTasks();

}
