package com.caitlinash.employeemanagement.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateEmployeeRequest {

    // ---- personal info ----

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    // ---- contact info ----

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^(\\+1\\s?)?\\(?[0-9]{3}\\)?[\\s.-]?[0-9]{3}[\\s.-]?[0-9]{4}$",
        message = "Phone number must be a valid US format"
    )
    private String phoneNumber;

    // ---- job info ----

    @NotBlank(message = "Position is required")
    private String position;

    @NotBlank(message = "Employee number is required")
    @Pattern(
        regexp = "^[A-Z]{2,3}[0-9]{4,6}$",
        // department code + number
        message = "Employee number must be 2-3 letters followed by 4-6 digits"
    )
    private String employeeNumber;

    @NotNull(message = "Salary is required")
    private BigDecimal salary;

    // status will default to ACTIVE in service layer (new employees should be active)

    // relationships 

    @NotNull(message = "Department is required")
    private long departmentId;

    @NotNull(message = "User account is required")
    private long userId;

    @NotNull(message = "Date of hire is required")
    private LocalDate hiredDate;

    // ---- default constructor ----

    public CreateEmployeeRequest() {}

    public CreateEmployeeRequest(String firstName, String lastName, String email, String phoneNumber,
    String position, String employeeNumber, BigDecimal salary, long departmentId, long userId, LocalDate hiredDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.departmentId = departmentId;
        this.userId = userId;
        this.hiredDate = hiredDate;
    }

    // ---- getters and setters ----

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

    // get department id 
    public long getDepartmentId() {
        return departmentId;
    }

    // set department id 
    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    // get user id 
    public long getUserId() {
        return userId;
    }

    // set user id
    public void setUserId(long userId) {
        this.userId = userId;
    }

    // get date of hire 
    public LocalDate getHiredDate() {
        return hiredDate;
    }

    // set date of hire
    public void setHiredDate(LocalDate hiredDate) {
        this.hiredDate = hiredDate;
    }

    // ---- security method ----

    // toString() method 
    @Override
    public String toString() {
        return "CreateEmployeeRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", salary='" + salary + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", userId='" + userId + '\'' +
                ", hiredDate='" + hiredDate + '\'' +
                '}';
    }
    
}
