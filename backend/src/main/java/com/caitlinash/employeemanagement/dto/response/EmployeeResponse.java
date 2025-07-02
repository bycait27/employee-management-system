package com.caitlinash.employeemanagement.dto.response;

import com.caitlinash.employeemanagement.enums.Status; 

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeResponse {
   
    // ---- employee data ----

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String position;
    private String employeeNumber;
    private BigDecimal salary;
    private Status status;
    private LocalDate hiredDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    
    // use user and department response data 
    private UserResponse user;          
    private DepartmentResponse department; 

    // ---- default constructor ----

    public EmployeeResponse() {}

    public EmployeeResponse(long id, String firstName, String lastName, String email, String phoneNumber,
                            String position, String employeeNumber, BigDecimal salary, Status status, LocalDate hiredDate,
                            LocalDateTime createdDate, LocalDateTime updatedDate, UserResponse user, DepartmentResponse department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.status = status;
        this.hiredDate = hiredDate;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
        this.department = department;
    }

    // ---- getters and setters ----

    // get id
    public long getId() {
        return id;
    }

    // set id
    public void setId(long id) {
        this.id = id;
    }

    // get first name 
    public String getFirstName() {
        return firstName;
    }

    // set first name 
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // get last name 
    public String getLastName() {
        return lastName;
    }

    // set last name 
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // get email 
    public String getEmail() {
        return email;
    }

    // set email
    public void setEmail(String email) {
        this.email = email;
    } 

    // get phone number
    public String getPhoneNumber() {
        return phoneNumber;
    } 

    // set phone number 
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    } 

    // get position
    public String getPosition() {
        return position;
    }

    // set position 
    public void setPosition(String position) {
        this.position = position;
    }

    // get employee number 
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    // set employee number 
        public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    // get salary 
    public BigDecimal getSalary() {
        return salary;
    }

    // set salary 
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    // get status 
    public Status getStatus() {
        return status;
    }

    // set status 
    public void setStatus(Status status) {
        this.status = status;
    }

    // get date of hire 
    public LocalDate getHiredDate() {
        return hiredDate;
    }

    // set date of hire
    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    // get created date 
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    // set created date 
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    // get updated date 
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    // set updated date 
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    // get user
    public UserResponse getUser() {
        return user;
    }

    // set user
    public void setUser(UserResponse user) {
        this.user = user;
    }

    // get department
    public DepartmentResponse getDepartment() {
        return department;
    }

    // set department
    public void setDepartment(DepartmentResponse department) {
        this.department = department;
    }

    // ---- security method ----

    // toString() method
    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' + 
                ", lastName='" + lastName + '\'' + 
                ", email='" + email + '\'' + 
                ", phoneNumber='" + phoneNumber + '\'' + 
                ", position='" + position + '\'' + 
                ", employeeNumber='" + employeeNumber + '\'' + 
                ", salary='" + salary + '\'' + 
                ", status='" + status + '\'' + 
                ", hiredDate='" + hiredDate + '\'' + 
                ", createdDate='" + createdDate + '\'' + 
                ", updatedDate='" + updatedDate + '\'' + 
                ", user='" + user + '\'' + 
                ", department='" + department + '\'' + 
                '}';
    }

}
