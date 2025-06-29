package com.caitlinash.employeemanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.caitlinash.employeemanagement.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(
    name = "employees",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"email"}
        ),
        @UniqueConstraint(
            columnNames = {"phoneNumber"}
        ),
        @UniqueConstraint(
            columnNames = {"employeeNumber"}
        )
    }
    )
public class Employee {

    // ---- data fields ----

    // id field 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq_gen")
    @SequenceGenerator(name = "employees_seq_gen", sequenceName = "employees_seq", allocationSize = 1)
    private long id;

    // first name field 
    @NotBlank(message = "First name is required")
    private String firstName;

    // last name field 
    @NotBlank(message = "Last name is required")
    private String lastName;

    // email field
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // phone number field 
    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^(\\\\+1\\\\s?)?\\\\(?[0-9]{3}\\\\)?[\\\\s.-]?[0-9]{3}[\\\\s.-]?[0-9]{4}$",
        message = "Phone number must be a valid US format"
    )
    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    // job title/position field 
    @NotBlank(message = "Position is required")
    private String position;

    // employee number 
    @NotBlank(message = "Employee number is required")
    @Pattern(
    regexp = "^[A-Z]{2,3}[0-9]{4,6}$",
    // department code + number
    message = "Employee number must be 2-3 letters followed by 4-6 digits"
    )
    @Column(name = "employeeNumber", nullable = false, unique = true)
    private String employeeNumber;

    // salary field 
    @NotNull(message = "Salary is required")
    private BigDecimal salary;

    // status field 
    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private Status status;
    
    // department field
    @NotNull(message = "Department is required")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // user field
    @NotNull(message = "User is required")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // date of hire field 
    @NotNull(message = "Date of hire is required")
    private LocalDate hiredDate;

    // created date 
    private LocalDateTime createdDate;

    // updated date 
    private LocalDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }

    // ---- getters and setters ----

    public Employee() {}

    public Employee(String firstName, String lastName, String email, String phoneNumber, 
    String position, String employeeNumber, BigDecimal salary, Status status, Department department, 
    User user, LocalDate hiredDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.employeeNumber = employeeNumber;
        this.salary = salary;
        this.status = status;
        this.department = department;
        this.user = user;
        this.hiredDate = hiredDate;
    }

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

    // get postion 
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

    // get department 
    public Department getDepartment() {
        return department;
    }

    // set department 
    public void setDepartment(Department department) {
        this.department = department;
    }

    // get user 
    public User getUser() {
        return user;
    }

    // set user 
    public void setUser(User user) {
        this.user = user;
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
    
}
