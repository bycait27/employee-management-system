package com.caitlinash.employeemanagement.dto.request;

import com.caitlinash.employeemanagement.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateUserRequest {

    // ---- update requirements ----

    @NotBlank(message = "Username is required")
    @Size(max = 15, message = "Username must be less than 15 characters")
    private String username; 

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email; 

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role; 

    // ---- default constructor ----

    public UpdateUserRequest() {}

    public UpdateUserRequest(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // ---- getters and setters ----

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

    // get password
    public String getPassword() {
        return password;
    }

    // set password
    public void setPassword(String password) {
        this.password = password;
    }

    // get role 
    public Role getRole() {
        return role;
    }

    // set role 
    public void setRole(Role role) {
        this.role = role;
    }

    // ---- security method ----

    // toString() method (keep password safe)
    @Override
    public String toString() {
        return "UpdateUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", role='" + role + '\'' +
                '}';
    }
    
}
