package com.caitlinash.employeemanagement.repository;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.caitlinash.employeemanagement.entity.Employee;
import com.caitlinash.employeemanagement.entity.Department;
import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.enums.Status;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // find queries
    List<Employee> findByFirstName(String firstName);
    List<Employee> findByLastName(String lastName); 
    Employee findByEmail(String email); 
    Employee findByPhoneNumber(String phoneNumber); 
    List<Employee> findByPosition(String position);
    Employee findByEmployeeNumber(String employeeNumber);
    List<Employee> findBySalary(BigDecimal salary); 
    List<Employee> findByStatus(Status status);  

    // check existence  
    boolean existsByEmail(String email); 
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmployeeNumber(String employeeNumber); 

    // relationship queries
    List<Employee> findByDepartment(Department department);
    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByDepartmentName(String departmentName);
    Employee findByUser(User user); 
    Employee findByUserId(Long userId);

    // date-based queries
    List<Employee> findByCreatedDate(LocalDateTime createdDate);
    List<Employee> findByCreatedDateBetween(LocalDateTime start, LocalDateTime end);
    Employee findByHiredDate(LocalDate hiredDate);

    // custom delete operations

    @Modifying
    @Transactional
    void deleteByEmail(String email); 

    @Modifying
    @Transactional
    void deleteByPhoneNumber(String phoneNumber); 

    @Modifying
    @Transactional
    void deleteByEmployeeNumber(String employeeNumber);
    
    // salary range queries
    List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary);
    List<Employee> findBySalaryGreaterThan(BigDecimal salary); 
    List<Employee> findBySalaryLessThan(BigDecimal salary); 

    // hire date ranges 
    List<Employee> findByHiredDateBetween(LocalDate startDate, LocalDate endDate); 
    List<Employee> findByHiredDateAfter(LocalDate date); 

    // combined queries
    List<Employee> findByDepartmentAndStatus(Department department, Status status); 
    List<Employee> findByStatusAndSalaryGreaterThan(Status status, BigDecimal salary);

    // search functionality
    List<Employee> findByFirstNameContainingIgnoreCase(String nameFragment);
    List<Employee> findByLastNameContainingIgnoreCase(String nameFragment); 
	
}
