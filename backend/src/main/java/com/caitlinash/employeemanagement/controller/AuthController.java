package com.caitlinash.employeemanagement.controller;

import com.caitlinash.employeemanagement.dto.request.CreateUserRequest;
import com.caitlinash.employeemanagement.dto.request.LoginRequest;
import com.caitlinash.employeemanagement.dto.response.JwtResponse;
import com.caitlinash.employeemanagement.dto.response.MessageResponse;
import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.enums.Role;
import com.caitlinash.employeemanagement.repository.UserRepository;
import com.caitlinash.employeemanagement.security.JwtUtils;
import com.caitlinash.employeemanagement.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AuthController - handles authentication endpoints (login/signup)
 * endpoints are public (no authentication required)
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    // login endpoint (validates credentials and returns JWT token)
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Step 1: check if user exists by username or email
        User user = userRepository.findByUsernameOrEmail(
            loginRequest.getUsernameOrEmail(), 
            loginRequest.getUsernameOrEmail()
        );

        if (user == null) {
            return ResponseEntity.badRequest()
                .body(new MessageResponse("Error: User not found!"));
        }

        // Step 2: authenticate user with Spring Security
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));

        // Step 3: set authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Step 4: generate JWT token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Step 5: get user details and roles
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        // Step 6: update last login time
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        // Step 7: return JWT response
        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
    }

    // signup endpoint (creates new user account)
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CreateUserRequest signUpRequest) {
        
        // Step 1: check if username already exists
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Step 2: check if email already exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Step 3: create new user with encrypted password
        User user = new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            encoder.encode(signUpRequest.getPassword()),  // encrypt password with BCrypt
            signUpRequest.getRole() != null ? signUpRequest.getRole() : Role.EMPLOYEE  // default role
        );

        // Step 4: save user to database
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}