package com.example.taskManagementApp.security;

import com.example.taskManagementApp.authResponse.AuthResponse;
import com.example.taskManagementApp.request.LoginRequest;
import com.example.taskManagementApp.user.CustomerUserDetailsService;
import com.example.taskManagementApp.user.User;
import com.example.taskManagementApp.user.UserRepository;
import com.example.taskManagementApp.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("signup")
    public AuthResponse registerUser(@RequestBody User user) throws Exception {

        User byEmail = userRepository.findByEmail(user.getEmail());

        if (byEmail != null) {
            throw new Exception("user with email: " + user.getEmail() + "already exists");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                savedUser.getEmail(),
                savedUser.getPassword()
        );

        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token, " registered successfully");
    }

    @GetMapping("/sign-in")
    public AuthResponse signIn(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        String token = JwtProvider.generateToken(authentication);

        return new AuthResponse(token, " login success");
    }

    private Authentication authenticate (String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException("invalid email or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("invalid email or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

}
