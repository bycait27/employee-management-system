package com.caitlinash.employeemanagement.repository;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.caitlinash.employeemanagement.entity.Department;

import jakarta.transaction.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // find queries
    Department findByName(String name);
    List<Department> findByLocation(String location);
   
    // data-based queries
    List<Department> findByCreatedDate(LocalDateTime createdDate);
    List<Department> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);

    // existence checks
    boolean existsByName(String name); 
    boolean existsByLocation(String location); 

    // custom delete operations 
    @Modifying
    @Transactional
    void deleteByName(String name);

    @Modifying
    @Transactional
    void deleteByLocation(String location);

    // relationship queries 
    @Query("SELECT d FROM Department d WHERE SIZE(d.employees) > 0")
    List<Department> findDepartmentsWithEmployees();
    // partial matching when user types letters in department names
    List<Department> findByNameContainingIgnoreCase(String nameFragment);
    
}
