package com.caitlinash.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caitlinash.employeemanagement.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // ...
    
}
