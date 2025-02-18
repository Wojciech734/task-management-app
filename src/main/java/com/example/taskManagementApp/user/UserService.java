package com.example.taskManagementApp.user;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id) throws Exception;
    User findUserByEmail(String email);
    User createNewAccount(User user) throws Exception;
    String deleteAccount(Long id) throws Exception;
    List<User> findAllUsers();
}
