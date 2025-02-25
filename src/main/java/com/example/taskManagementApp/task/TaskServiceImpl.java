package com.example.taskManagementApp.task;

import com.example.taskManagementApp.user.User;
import com.example.taskManagementApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Override
    public Task createNewTask(Task task, Long userId) throws Exception {
        Optional<User> userOptional = userService.findUserById(userId);
        if (userOptional.isEmpty()) {
            throw new Exception("User with ID: " + userId + " not found.");
        }

        User user = userOptional.get();
        Task newTask = new Task();
        newTask.setCompleted(false);
        newTask.setUser(user);
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setDueDate(task.getDueDate());

        return taskRepository.save(newTask);
    }

    @Override
    public Task editTask(Task newTask, Long taskId) throws Exception {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (taskOptional.isEmpty()) {
            throw new Exception("Task with ID: " + taskId + " not found.");
        }

        Task taskToEdit = taskOptional.get();
        taskToEdit.setTitle(newTask.getTitle());
        taskToEdit.setDescription(newTask.getDescription());
        taskToEdit.setCompleted(newTask.isCompleted());
        taskToEdit.setDueDate(newTask.getDueDate());

        return taskRepository.save(taskToEdit);
    }

    @Override
    public String deleteTask(Long id) throws Exception {
        Optional<Task> taskById = taskRepository.findById(id);
        if (taskById.isEmpty()) {
            throw new Exception("task with ID: " + id + " not found");
        }
        taskRepository.deleteById(id);
        return "task has been deleted successfully";
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
