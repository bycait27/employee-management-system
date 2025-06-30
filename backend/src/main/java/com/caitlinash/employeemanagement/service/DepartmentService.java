package com.caitlinash.employeemanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.caitlinash.employeemanagement.entity.Department;
import com.caitlinash.employeemanagement.entity.Employee;
import com.caitlinash.employeemanagement.repository.DepartmentRepository;
import com.caitlinash.employeemanagement.repository.EmployeeRepository;

@Service
public class DepartmentService {

    // ---- dependency injections ----

    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    // ---- methods ----

    // validation method 
    private void validateDepartmentData(String name) {
        if (departmentRepository.existsByName(name)) {
            throw new RuntimeException("Department already exists with that name: " + name);
        }
    }

    // ---- CRUD operations ----

    // create a new department
    public Department createDepartment(String name, String description, String location) {
        // validate name before saving new department
        validateDepartmentData(name);

        // create new user 
        Department department = new Department();

        department.setName(name);
        department.setDescription(description);
        department.setLocation(location);

        return departmentRepository.save(department);
    }

    // get a department by id 
    public Department getDepartmentById(long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with this id: " + id));
    }

    // get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // get a department by name 
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    // get departments by location
    public List<Department> getDepartmentByLocation(String location) {
        return departmentRepository.findByLocation(location);
    }

    // get departments by created date 
    public List<Department> getDepartmentByCreatedDate(LocalDateTime createdDate) {
        return departmentRepository.findByCreatedDate(createdDate);
    }

    // get departments between two dates 
    public List<Department> getDepartmentByCreatedDateBetween(LocalDateTime start, LocalDateTime end) {
        return departmentRepository.findByCreatedDateBetween(start, end);
    }

    // get all departments by searching (name)
    public List<Department> getDepartmentsByNameContainingIgnoreCase(String nameFragment) {
        return departmentRepository.findByNameContainingIgnoreCase(nameFragment);
    }

    // does the department exist by this name? 
    public boolean checkExistenceByName(String name) {
        return departmentRepository.existsByName(name);
    }

    // does the department exist by this location?
    public boolean checkExistenceByLocation(String location) {
        return departmentRepository.existsByLocation(location);
    }

    // edit a department by id 
    public Department editDepartmentById(long id, String name, String description, String location) {
        // find existing department
        Department currentDepartment = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found with this id: " + id));

        // only validate if name is changing
        if (!currentDepartment.getName().equals(name)) {
            validateDepartmentData(name);
        }

        // update its fields 
        currentDepartment.setName(name);
        currentDepartment.setDescription(description);
        currentDepartment.setLocation(location);

        // save and return 
        return departmentRepository.save(currentDepartment);
    }

    // delete a department by id 
    public void deleteDepartmentById(long id) {
        Department department = getDepartmentById(id);

        // check if department has employees before deleting 
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (!employees.isEmpty()) {
            throw new RuntimeException("Cannot delete department with " + employees.size() + " employees. Please reassign employees first.");
        }

        departmentRepository.deleteById(id);
    }

    // delete a department by name
    public void deleteDepartmentByName(String name) {
        Department department = getDepartmentByName(name);

        // check if department with that name has employees before deleting
        List<Employee> employees = employeeRepository.findByDepartment(department);
        if (!employees.isEmpty()) {
            throw new RuntimeException("Cannot delete department with " + name + "with " + employees.size() + " employees. Please reassign employees first.");
        }

        departmentRepository.deleteByName(name);
    }

    // delete a department by location
    public void deleteDepartmentByLocation(String location) {
        List<Department> departments = getDepartmentByLocation(location);

        // check if departments at that location have employees before deleting
        for (Department dept : departments) {
                List<Employee> employees = employeeRepository.findByDepartment(dept);
                if (!employees.isEmpty()) {
                    throw new RuntimeException("Cannot delete departments in location '" + location + "'. Department '" + dept.getName() + "' has " + employees.size() + " employees.");
                }
        }

        departmentRepository.deleteByLocation(location);
    }

    // ---- relationship query operations ----

    // get departments that have employees
    public List<Department> getDepartmentsWithEmployees() {
        return departmentRepository.findDepartmentsWithEmployees();
    }

    // get all employees in a department
    public List<Employee> getDepartmentEmployees(long departmentId) {
        Department department = getDepartmentById(departmentId);
        return employeeRepository.findByDepartment(department);
    }

    // get employee count for a department 
    public long getDepartmentEmployeeCount(long departmentId) {
        Department department = getDepartmentById(departmentId);
        return employeeRepository.findByDepartment(department).size();
    }
    
}
