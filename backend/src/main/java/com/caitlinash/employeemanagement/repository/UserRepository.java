package com.caitlinash.employeemanagement.repository;

import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.enums.Role;

import jakarta.transaction.Transactional;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // authentication queries 
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameOrEmail(String username, String email);

    // existence checks (validation)
    boolean existsByUsername(String username);
    boolean existsByEmail(String email); 

    // role-based queries
    List<User> findByRole(Role role);

    // date-based queries
    List<User> findByCreatedDate(LocalDateTime createdDate);
    List<User> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);

    // custom delete operations

    @Modifying
    @Transactional
    void deleteByUsername(String username);

    @Modifying
    @Transactional
    void deleteByRole(Role role);

}
