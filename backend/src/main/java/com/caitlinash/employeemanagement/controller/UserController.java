package com.caitlinash.employeemanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caitlinash.employeemanagement.dto.request.CreateUserRequest;
import com.caitlinash.employeemanagement.dto.request.LoginRequest;
import com.caitlinash.employeemanagement.dto.response.UserResponse;
import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // ---- constructor injection ----

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---- helper method to convert entities to dtos ----
    private UserResponse convertToDto(User user) {
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole(),
            user.getCreatedDate(),
            user.getLastLogin()
        );
    }

    // ---- REST APIs ----

    // create a new user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.createUser(
        request.getUsername(),
        request.getEmail(),
        request.getPassword(),
        request.getRole()
        );
    
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(user));
    }

    // login with credentials
    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        User user = userService.authenticateUser(
            request.getUsernameOrEmail(), 
            request.getPassword()
        );

        return ResponseEntity.ok(convertToDto(user));
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        User user = userService.getUserById(id);

        return ResponseEntity.ok(convertToDto(user));
    }

    // get all users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userService.getAllUsers();

        List<UserResponse> userResponses = users.stream()
        .map(this::convertToDto)  
        .collect(Collectors.toList());
        
        return ResponseEntity.ok(userResponses);
    }

    // todo update user by id
    // @PutMapping("/{id}")
    // public ResponseEntity<UserResponse> updateUserById(@PathVariable long id, ) {

    // }

    // delete user by id
    @DeleteMapping("/id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);

        return ResponseEntity.noContent().build();  // 204 No Content
    }
    
}