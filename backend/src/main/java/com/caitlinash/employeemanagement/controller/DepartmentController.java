package com.caitlinash.employeemanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caitlinash.employeemanagement.dto.request.CreateDepartmentRequest;
import com.caitlinash.employeemanagement.dto.request.UpdateDepartmentRequest;
import com.caitlinash.employeemanagement.dto.response.DepartmentResponse;
import com.caitlinash.employeemanagement.entity.Department;
import com.caitlinash.employeemanagement.service.DepartmentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    // ---- constructor injection ----

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // ---- helper method to convert entities to dtos ----
    private DepartmentResponse convertToDto(Department department) {
        return new DepartmentResponse(
            department.getId(),
            department.getName(),
            department.getDescription(),
            department.getLocation(),
            department.getCreatedDate(),
            department.getUpdatedDate()
        );
    }

    // ---- REST APIs ----

    // create a new department 
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@Valid @RequestBody CreateDepartmentRequest request) {
        Department department = departmentService.createDepartment(
            request.getName(), 
            request.getDescription(), 
            request.getLocation()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(department));
    }
    

    // get department by id
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
        Department department = departmentService.getDepartmentById(id);

        return ResponseEntity.ok(department);
    }

    // get all departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }
    

    // update department by id
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartmentById(@PathVariable long id, @Valid @RequestBody 
                                                           UpdateDepartmentRequest request) {
        Department department = departmentService.editDepartmentById(
            id,
            request.getName(), 
            request.getDescription(), 
            request.getLocation()
        );

        return ResponseEntity.ok(convertToDto(department));
    }

    // delete department by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable long id) {
        departmentService.deleteDepartmentById(id);

        return ResponseEntity.noContent().build();
    }
    
}