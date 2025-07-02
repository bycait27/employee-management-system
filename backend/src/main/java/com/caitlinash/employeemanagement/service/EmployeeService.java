package com.caitlinash.employeemanagement.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.caitlinash.employeemanagement.enums.Status;

import org.springframework.stereotype.Service;

import com.caitlinash.employeemanagement.entity.Department;
import com.caitlinash.employeemanagement.entity.Employee;
import com.caitlinash.employeemanagement.entity.User;
import com.caitlinash.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    // * ---- dependency injections ----

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // * ---- methods ----

    // validation method 
    private void validateEmployeeData(String email, String phoneNumber, String employeeNumber) {
        if (employeeRepository.existsByEmail(email)) {
            throw new RuntimeException("Employee already exists with that email: " + email);
        }
        if (employeeRepository.existsByPhoneNumber(phoneNumber)) {
            throw new RuntimeException("Employee already exists with that phone number: " + phoneNumber);
        }
        if (employeeRepository.existsByEmployeeNumber(employeeNumber)) {
            throw new RuntimeException("Employee already exists with that employee number: " + employeeNumber);
        }
    }

    // ? ---- CRUD operations -----

    // create a new employee
    public Employee createEmployee(String firstName, String lastName, String email, String phoneNumber,
    String position, String employeeNumber, BigDecimal salary, Status status, Department department,
    User user, LocalDate hiredDate) {
        // validate email, phone number, and employee number before saving new employee
        validateEmployeeData(email, phoneNumber, employeeNumber);

        // create new employee
        Employee employee = new Employee();

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPhoneNumber(phoneNumber);
        employee.setPosition(position);
        employee.setEmployeeNumber(employeeNumber);
        employee.setSalary(salary);
        employee.setStatus(Status.ACTIVE);
        employee.setDepartment(department);
        employee.setUser(user);
        employee.setHiredDate(hiredDate);

        return employeeRepository.save(employee);
    }

    // get an employee by id 
    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found with this id: " + id));
    }

    // get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // get employees by first name
    public List<Employee> getEmployeesByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    // get employees by last name 
    public List<Employee> getEmployeesByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    // get an employee by email 
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // get an employee by phone number
    public Employee getEmployeeByPhoneNumber(String phoneNumber) {
        return employeeRepository.findByPhoneNumber(phoneNumber);
    }

    // get employees by position
    public List<Employee> getEmployeesByPosition(String position) {
        return employeeRepository.findByPosition(position);
    }

    // get an employee by employee number 
    public Employee getEmployeeByEmployeeNumber(String employeeNumber) {
        return employeeRepository.findByEmployeeNumber(employeeNumber);
    }

    // get employees by salary
    public List<Employee> getEmployeesBySalary(BigDecimal salary) {
        return employeeRepository.findBySalary(salary);
    }

    // get employees by status
    public List<Employee> getEmployeesByStatus(Status status) {
        return employeeRepository.findByStatus(status);
    }

    // get employees by created date 
    public List<Employee> getEmployeesByCreatedDate(LocalDateTime createdDate) {
        return employeeRepository.findByCreatedDate(createdDate);
    }

    // get employees between two dates
    public List<Employee> getEmployeesByCreatedDateBetween(LocalDateTime start, LocalDateTime end) {
        return employeeRepository.findByCreatedDateBetween(start, end);
    }

    // get employees by date of hire
    public List<Employee> getEmployeesByHiredDate(LocalDate hiredDate) {
        return employeeRepository.findByHiredDate(hiredDate);
    }

    // edit an employee by id
    public Employee editEmployeeById(long id, String newFirstName, String newLastName, String newEmail, String newPhoneNumber,
    String newPosition, String newEmployeeNumber, BigDecimal newSalary, Status newStatus, Department newDepartment,
    User newUser, LocalDate newHiredDate) {
        // find existing employee
        Employee currentEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with this id: " + id));

        // only validate if email, phone number, or employee number is changing
        if (!currentEmployee.getEmail().equals(newEmail) && employeeRepository.existsByEmail(newEmail)) {
            throw new RuntimeException("Employee already exists with that email: " + newEmail);
        }
        if (!currentEmployee.getPhoneNumber().equals(newPhoneNumber) && employeeRepository.existsByPhoneNumber(newPhoneNumber)) {
            throw new RuntimeException("Employee already exists with that phone number: " + newPhoneNumber);
        }
        if (!currentEmployee.getEmployeeNumber().equals(newEmployeeNumber) && employeeRepository.existsByEmployeeNumber(newEmployeeNumber)) {
            throw new RuntimeException("Employee already exists with that employee number: " + newEmployeeNumber);
        }

        // update its fields
        currentEmployee.setFirstName(newFirstName);
        currentEmployee.setLastName(newLastName);
        currentEmployee.setEmail(newEmail);
        currentEmployee.setPhoneNumber(newPhoneNumber);
        currentEmployee.setPosition(newPosition);
        currentEmployee.setEmployeeNumber(newEmployeeNumber);
        currentEmployee.setSalary(newSalary);
        currentEmployee.setStatus(newStatus);
        currentEmployee.setDepartment(newDepartment);
        currentEmployee.setUser(newUser);
        currentEmployee.setHiredDate(newHiredDate);

        // save and return
        return employeeRepository.save(currentEmployee);
    }

    // delete an employee by id
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    // delete an employee by email 
    public void deleteEmployeeByEmail(String email) {
        employeeRepository.deleteByEmail(email);
    }

    // delete an employee by phone number
    public void deleteEmployeeByPhoneNumber(String phoneNumber) {
        employeeRepository.deleteByPhoneNumber(phoneNumber);
    }

    // delete an employee by employee number
    public void deleteEmployeeByEmployeeNumber(String employeeNumber) {
        employeeRepository.deleteByEmployeeNumber(employeeNumber);
    }

    // ? ---- existence checks ----

    // does the employee exist by this email?
    public boolean checkExistenceByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    // does the employee exist by this phone number?
    public boolean checkExistenceByPhoneNumber(String phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

    // does the employee exist by this employee number?
    public boolean checkExistenceByEmployeeNumber(String employeeNumber) {
        return employeeRepository.existsByEmployeeNumber(employeeNumber);
    }

    // ? ---- relationship query operations ----

    // get employees by department
    public List<Employee> getEmployeesByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    // get employees by department id 
    public List<Employee> getEmployeesByDepartmentId(long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
    
    // get employees by department name
    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    // get an employee by user 
    public Employee getEmployeeByUser(User user) {
        return employeeRepository.findByUser(user);
    }

    // get an employee by user id 
    public Employee getEmployeeByUserId(long userId) {
        return employeeRepository.findByUserId(userId);
    }

    // ? ---- employee management ----

    // activate an inactive employee
    public Employee activateEmployee(long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employee.setStatus(Status.ACTIVE);
        return employeeRepository.save(employee);
    }

    // deactivate an active employee
    public Employee deactivateEmployee(long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employee.setStatus(Status.INACTIVE);
        return employeeRepository.save(employee);
    }

    // transfer an employee to a different department
    public Employee transferEmployee(long employeeId, Department newDepartment) {
        Employee employee = getEmployeeById(employeeId);
        employee.setDepartment(newDepartment);
        return employeeRepository.save(employee);
    } 

    // ? ---- query operations for HR ----

    // get employees by salary in between (min and max nums)
    public List<Employee> getEmployeesBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary) {
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    // get employees by salary (greater than)
    public List<Employee> getEmployeesBySalaryGreaterThan(BigDecimal salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }
    
    // get employees by salary (less than)
    public List<Employee> getEmployeesBySalaryLessThan(BigDecimal salary) {
        return employeeRepository.findBySalaryLessThan(salary);
    }
    
    // get employees by date of hire (date in between two certain dates)
    public List<Employee> getEmployeesByHiredDateBetween(LocalDate startDate, LocalDate endDate) {
        return employeeRepository.findByHiredDateBetween(startDate, endDate);
    }
    
    // get employees by date of hire (after certain date)
    public List<Employee> getEmployeesByHiredDateAfter(LocalDate date) {
        return employeeRepository.findByHiredDateAfter(date);
    }
    
    // get employees by department AND status
    public List<Employee> getEmployeesByDepartmentAndStatus(Department department, Status status) {
        return employeeRepository.findByDepartmentAndStatus(department, status);
    }
    
    // get employees by status AND salary (greater than)
    public List<Employee> getEmployeesByStatusAndSalaryGreaterThan(Status status, BigDecimal salary) {
        return employeeRepository.findByStatusAndSalaryGreaterThan(status, salary);
    }
    
    // get employees by first name (searching)
    public List<Employee> getEmployeesByFirstNameContainingIgnoreCase(String nameFragment) {
        return employeeRepository.findByFirstNameContainingIgnoreCase(nameFragment);
    }
    
    // get employees by last name (searching)
    public List<Employee> getEmployeesByLastNameContainingIgnoreCase(String nameFragment) {
        return employeeRepository.findByLastNameContainingIgnoreCase(nameFragment);
    }

    // get active employee count
    public long getActiveEmployeeCount() {
        return employeeRepository.findByStatus(Status.ACTIVE).size();
    }

    // get the average salary for a department
    public BigDecimal getAverageSalaryByDepartment(Department department) {
        List<Employee> employees = employeeRepository.findByDepartment(department);
        return employees.stream()
            .map(Employee::getSalary)
            .reduce(BigDecimal.ZERO, BigDecimal::add) 
            .divide(BigDecimal.valueOf(employees.size()), 2, RoundingMode.HALF_UP);
    }
    
}
