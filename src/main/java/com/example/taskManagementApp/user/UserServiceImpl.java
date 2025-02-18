package com.example.taskManagementApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUserById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createNewAccount(User user) throws Exception{
        if (user == null || user.getEmail() == null) {
            throw new IllegalAccessException("user and email must not be null");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new Exception("A user with the given email address already exitsts ");
        }
        return userRepository.save(user);
    }

    @Override
    public String deleteAccount(Long id) throws Exception{
        if (id == null) {
            throw new IllegalAccessException("User ID must not be null");
        }
        if (!userRepository.existsById(id)) {
            throw new Exception("No user with the given ID");
        }
        userRepository.deleteById(id);
        return "account has been deleted successfully";
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}
