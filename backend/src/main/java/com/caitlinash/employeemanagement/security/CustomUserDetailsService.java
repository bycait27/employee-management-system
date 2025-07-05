package com.caitlinash.employeemanagement.security;

import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomUserDetailsService - loads user data for Spring Security authentication
 * this is called automatically when someone tries to log in
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * this method is called by Spring Security during login
     * it takes a username and returns UserDetails (UserPrincipal)
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Step 1: look up user in database
        User user = userRepository.findByUsername(username);
        
        // Step 2: if user doesn't exist, throw exception
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found: " + username);
        }

        // Step 3: convert User entity to UserPrincipal and return
        return UserPrincipal.create(user);
    }
}
