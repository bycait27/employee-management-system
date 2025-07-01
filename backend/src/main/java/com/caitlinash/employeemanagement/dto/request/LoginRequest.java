package com.caitlinash.employeemanagement.dto.request;

import java.lang.Override;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

    // ---- validation requirements ----
    
    @NotBlank(message = "Username or email is required")
    private String usernameOrEmail; // user can choose either username or email to login

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;

    // ---- default constructor and args ----

    public LoginRequest() {}

    public LoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    // ---- getters and setters ----

    // get username or email
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    // set username or email 
    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    // get password
    public String getPassword() {
        return password;
    }

    // set password
    public void setPassword(String password) {
        this.password = password;
    }

    // ---- security method ----

    // toString() method (keep password safe)
    @Override
    public String toString() {
        return "LoginRequest{" +
                "usernameOrEmail='" + usernameOrEmail + '\'' +
                ", password='[PROTECTED]'" +
                '}';
    }
    
}
