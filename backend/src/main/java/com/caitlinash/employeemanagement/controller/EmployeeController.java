package com.caitlinash.employeemanagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caitlinash.employeemanagement.dto.request.CreateEmployeeRequest;
import com.caitlinash.employeemanagement.dto.request.UpdateEmployeeRequest;
import com.caitlinash.employeemanagement.dto.response.DepartmentResponse;
import com.caitlinash.employeemanagement.dto.response.EmployeeResponse;
import com.caitlinash.employeemanagement.dto.response.UserResponse;
import com.caitlinash.employeemanagement.entity.Employee;
import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.entity.Department;
import com.caitlinash.employeemanagement.enums.Status;

import com.caitlinash.employeemanagement.service.DepartmentService;
import com.caitlinash.employeemanagement.service.EmployeeService;
import com.caitlinash.employeemanagement.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // ---- constructor injection ----

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final UserService userService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService, 
                              UserService userService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.userService = userService;
    }

    // ---- helper method to convert entities to dtos ----
    private EmployeeResponse convertToDto(Employee employee) {
            // convert User entity → UserResponse DTO
        UserResponse userResponse = new UserResponse(
            employee.getUser().getId(),
            employee.getUser().getUsername(),
            employee.getUser().getEmail(),
            employee.getUser().getRole(),
            employee.getUser().getCreatedDate(),
            employee.getUser().getLastLogin()
        );
        
        // convert Department entity → DepartmentResponse DTO
        DepartmentResponse departmentResponse = new DepartmentResponse(
            employee.getDepartment().getId(),
            employee.getDepartment().getName(),
            employee.getDepartment().getDescription(),
            employee.getDepartment().getLocation(),
            employee.getDepartment().getCreatedDate(),
            employee.getDepartment().getUpdatedDate()
        );
        
        // EmployeeResponse with converted DTOs
        return new EmployeeResponse(
            employee.getId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getEmail(),
            employee.getPhoneNumber(),
            employee.getPosition(),
            employee.getEmployeeNumber(),
            employee.getSalary(),
            employee.getStatus(),
            employee.getHiredDate(),
            employee.getCreatedDate(),
            employee.getUpdatedDate(),
            userResponse,           
            departmentResponse      
        );
    }

    // ---- REST APIs ----

    // create a new employee
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        User user = userService.getUserById(request.getUserId());
        Department department = departmentService.getDepartmentById(request.getDepartmentId());

        Employee employee = employeeService.createEmployee(
            request.getFirstName(), 
            request.getLastName(), 
            request.getEmail(), 
            request.getPhoneNumber(), 
            request.getPosition(), 
            request.getEmployeeNumber(), 
            request.getSalary(),  
            Status.ACTIVE, 
            department, 
            user, 
            request.getHiredDate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(employee));
    }

    // get employee by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable long id) {
        Employee employee= employeeService.getEmployeeById(id);

        return ResponseEntity.ok(convertToDto(employee));
    }

    // get all employees
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        List<EmployeeResponse> employeeResponses = employees.stream()
        .map(this::convertToDto)  
        .collect(Collectors.toList());
        
        return ResponseEntity.ok(employeeResponses);
    }

    // update employee by id
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeById(@PathVariable long id, @Valid @RequestBody UpdateEmployeeRequest request) {
        Department department = departmentService.getDepartmentById(request.getDepartmentId());

        Employee employee = employeeService.editEmployeeById(
            id, 
            request.getFirstName(), 
            request.getLastName(), 
            request.getEmail(), 
            request.getPhoneNumber(), 
            request.getPosition(), 
            request.getSalary(), 
            request.getStatus(), 
            department
        );

        return ResponseEntity.ok(convertToDto(employee));
    }

    // delete employee by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable long id) {
        employeeService.deleteEmployeeById(id);

        return ResponseEntity.noContent().build();  // 204 No Content
    }
    
}