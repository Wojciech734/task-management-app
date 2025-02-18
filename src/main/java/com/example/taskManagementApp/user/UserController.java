package com.example.taskManagementApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String testController() {
        return "user controller";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> findUserById(@PathVariable("id") Long id) throws Exception {
        return userService.findUserById(id);
    }

    @PostMapping
    public User createAccount(@RequestBody() User user) throws Exception {
        return userService.createNewAccount(user);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(@PathVariable("id") Long id) throws Exception {
        return userService.deleteAccount(id);
    }

}
