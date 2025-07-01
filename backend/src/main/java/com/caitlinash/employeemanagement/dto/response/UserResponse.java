package com.caitlinash.employeemanagement.dto.response;

import java.time.LocalDateTime;

import com.caitlinash.employeemanagement.enums.Role;

public class UserResponse {

    // ---- fields included ----

    private long id;
    private String username;
    private String email;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime lastLogin;

    // ----deault constructor and args ----

    public UserResponse() {}

    public UserResponse(long id, String username, String email, Role role, LocalDateTime createdDate, LocalDateTime lastLogin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
        this.lastLogin = lastLogin;
    }

    // ---- getters and setters ----

    // get id 
    public long getId() {
        return id;
    }

    // set id 
    public void setId(long id) {
        this.id = id;
    }

    // get username 
    public String getUsername() {
        return username;
    }

    // set username 
    public void setUsername(String username) {
        this.username = username;
    }

    // get email 
    public String getEmail() {
        return email;
    }

    // set email
    public void setEmail(String email) {
        this.email = email;
    } 

    // get role 
    public Role getRole() {
        return role;
    }

    // set role 
    public void setRole(Role role) {
        this.role = role;
    }

    // get created date
    public LocalDateTime getCreatedDate() {
        return createdDate;
    } 

    // set created date 
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // get last login 
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    // set last login
    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    // toString() method
    @Override
    public String toString() {
        return "UserResponse{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' + 
                ", email='" + email + '\'' + 
                ", role='" + role + '\'' + 
                ", createdDate='" + createdDate + '\'' + 
                ", lastLogin='" + lastLogin + '\'' + 
                '}';
    }
    
}
