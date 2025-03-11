package com.example.taskManagementApp.task;

import com.example.taskManagementApp.user.User;
import com.example.taskManagementApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Task> findAllTasks() {
        return taskService.findAllTasks();
    }

    @PostMapping
    public Task createTask(
            @RequestBody() Task task,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return taskService.createNewTask(task, user.getId());
    }

    @DeleteMapping("/{taskId}")
    public String deleteTask(
            @PathVariable("taskId") Long taskId
    ) throws Exception {
        return taskService.deleteTask(taskId);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(
            @RequestBody() Task newTask,
            @PathVariable("taskId") Long taskId
    ) throws Exception {
        return taskService.editTask(newTask, taskId);
    }

}
