package com.caitlinash.employeemanagement.entity;

import java.time.LocalDateTime;

import com.caitlinash.employeemanagement.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"username"}
        ),
        @UniqueConstraint(
            columnNames = {"email"}
        )
    }
    )
public class User {

    // ---- data fields ----

    // id field 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq_gen")
    @SequenceGenerator(name = "users_seq_gen", sequenceName = "users_seq", allocationSize = 1)
    private long id;

    // username field
    @NotBlank(message = "Username is required")
    @Size(max = 15, message = "Username must be less than 15 characters")
    @Column(name = "username", nullable = false, unique = true)
    private String username; 

    // email field
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // password field
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password; 

    // role field
    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    // created date field 
    private LocalDateTime createdDate;

    // last login field
    private LocalDateTime lastLogin;

    @PrePersist 
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    // ---- getters and setters ----

    public User() {}

    public User(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
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

}
