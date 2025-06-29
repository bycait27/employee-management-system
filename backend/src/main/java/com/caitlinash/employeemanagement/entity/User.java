package com.caitlinash.employeemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    // ---- data fields ----

    // id field 
    @Id
    @GeneratedValue()
    private long id;

    // username field
    @NotBlank(message = "Username is required")
    @Size(max = 15, message = "Username must be less than 15 characters")
    private String username; 

    // email field
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    // password field
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password; 

    // role field
    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    // enum role 

    // ---- getters and setters ----

    public User() {}

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        // this.role = role;
    }

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

    // set password
    public void setPassword(String password) {
        this.password = password;
    }

    // get role
    

    // set role

}
