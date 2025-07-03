package com.caitlinash.employeemanagement.dto.request;

import com.caitlinash.employeemanagement.enums.Status;

import java.math.BigDecimal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateEmployeeRequest {

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

    @NotNull(message = "Salary is required")
    private BigDecimal salary;

    @NotNull(message = "Status is required")
    private Status status;

    // relationships 

    @NotNull(message = "Department is required")
    private long departmentId;

    // ---- default constructor ----

    public UpdateEmployeeRequest() {}

    public UpdateEmployeeRequest(String firstName, String lastName, String email, String phoneNumber,
    String position, BigDecimal salary, Status status, long departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.salary = salary;
        this.status = status;
        this.departmentId = departmentId;
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

    // get department id 
    public long getDepartmentId() {
        return departmentId;
    }

    // set department id 
    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    // ---- security method ----

    // toString() method 
    @Override
    public String toString() {
        return "UpdateEmployeeRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", position='" + position + '\'' +
                ", salary='" + salary + '\'' +
                ", status='" + status + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }
    
}