package com.caitlinash.employeemanagement.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * AuthEntryPointJwt - handles unauthorized access attempts
 * called when someone tries to access a protected endpoint without proper authentication
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    /**
     * this method is called when an unauthenticated user tries to access a protected resource
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        
        System.err.println("Unauthorized error: " + authException.getMessage());
        
        // send 401 Unauthorized response
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}
