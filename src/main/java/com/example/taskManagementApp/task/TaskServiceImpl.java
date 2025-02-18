package com.example.taskManagementApp.task;

import com.example.taskManagementApp.user.User;
import com.example.taskManagementApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Override
    public Task createNewTask(Task task, Long userId) throws Exception {

        Optional<User> user = userService.findUserById(userId);

        Task newTask = new Task();
        newTask.setCompleted(false);
        newTask.setUser(user.get());
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setDueDate(task.getDueDate());

        return taskRepository.save(newTask);
    }

    @Override
    public Task editTask(Task newTask, Long taskId) throws Exception {

        Optional<Task> taskToEdit = taskRepository.findById(taskId);

        return null;
    }

    @Override
    public String deleteTask(Long id) {
        taskRepository.deleteById(id);
        return "task has been deleted successfully";
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
