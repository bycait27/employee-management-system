package com.caitlinash.employeemanagement.config;

import com.caitlinash.employeemanagement.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * WebSecurityConfig - main Spring Security configuration
 * this configures authentication, authorization, and security rules
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)  // enables @PreAuthorize annotations
public class WebSecurityConfig {

    @Autowired
    CustomUserDetailsService userDetailsService;

    // password encoder bean (uses BCrypt for hashing passwords)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // authentication provider (tells Spring Security how to authenticate users)
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);  // use custom service
        authProvider.setPasswordEncoder(passwordEncoder());      // use BCrypt
        return authProvider;
    }

    // authentication manager (handles the authentication process)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // security filter chain (defines which endpoints need authentication)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // disable CSRF for API (using JWT)
            .csrf(csrf -> csrf.disable())
            
            // configure session management (stateless for JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // configure authorization rules
            .authorizeHttpRequests(auth -> 
                auth
                    // public endpoints (no authentication needed)
                    .requestMatchers("/api/auth/**").permitAll()          // login/signup
                    .requestMatchers("/h2-console/**").permitAll()        // H2 database console
                    .requestMatchers("/actuator/**").permitAll()          // Spring Boot actuator
                    
                    // role-based access control
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")    // Admin only endpoints
                    .requestMatchers("/api/hr/**").hasAnyRole("ADMIN", "HR")  // Admin or HR
                    
                    // all other endpoints require authentication
                    .anyRequest().authenticated()
            );

        // allow H2 console frames (for development)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        // use our authentication provider
        http.authenticationProvider(authenticationProvider());

        return http.build();
    }
}
