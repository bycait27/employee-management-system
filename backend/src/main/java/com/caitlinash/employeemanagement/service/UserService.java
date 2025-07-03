package com.caitlinash.employeemanagement.service;

import com.caitlinash.employeemanagement.enums.Role;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.repository.UserRepository;

@Service
public class UserService {

    // ---- dependency injections ----
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ---- methods ----

    // validation method 
    private void validateUserData(String username, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User already exists with that username: " + username);
        }
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("User already exists with that email: " + email);
        }
    }

    // authenticate user (login functionality) 
    public User authenticateUser(String usernameOrEmail, String rawPassword) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // update last login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }

    // ---- CRUD operations ----

    // create a new user
    public User createUser(String username, String email, String password, Role role) {
        // validate username and email before saving new user
        validateUserData(username, email);

        // create new user
        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    // get a user by id
    public User getUserById(long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with this id: " + id));
    }

    // get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // get a user by username
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // get a user by email 
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // get a user by username OR email 
    public User getUserByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    // does the user exist by this username?
    public boolean checkExistenceByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // does the user exist by this email?
        public boolean checkExistenceByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // get users by role
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // get users by created date 
    public List<User> getUsersByCreatedDate(LocalDateTime createdDate) {
        return userRepository.findByCreatedDate(createdDate);
    }

    // get users between two dates 
    public List<User> getUsersByCreatedDateBetween(LocalDateTime start, LocalDateTime end) {
        return userRepository.findByCreatedDateBetween(start, end);
    }

    // edit a user by id
    public User editUserById(long id, String newUsername, String newEmail, String newPassword, Role newRole) {
        // find existing user
        User currentUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id: " + id));
        // update its fields
        currentUser.setUsername(newUsername);
        currentUser.setEmail(newEmail);

        // only encode if new password is provided 
        if (newPassword != null && !newPassword.isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
        }

        currentUser.setRole(newRole);
        // save and return
        return userRepository.save(currentUser);
    }

    // delete a user by id
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    // delete a user by username
    public void deleteUserByUsername(String username) {
        userRepository.deleteByUsername(username);
    } 

    // delete a user by role 
    public void deleteUserByRole(Role role) {
        userRepository.deleteByRole(role);
    }
    
}
