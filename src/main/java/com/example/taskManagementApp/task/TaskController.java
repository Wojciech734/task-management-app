package com.example.taskManagementApp.task;

import com.example.taskManagementApp.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping("/{userId}")
    public Task createTask(@RequestBody() Task task, @PathVariable("userId") Long userId) throws Exception {
        return taskService.createNewTask(task, userId);
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(@PathVariable("taskId") Long taskId) {
        return null;
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable("taskId") Long taskId) {
        return null;
    }

}
