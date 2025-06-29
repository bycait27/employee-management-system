package com.caitlinash.employeemanagement.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(
    name = "employees",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"email"}
        ),
        @UniqueConstraint(
            columnNames = {"phoneNumber"}
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
    @Column(name = "phoneNumber", nullable = false, unique = true)
    private String phoneNumber;

    // job title/position field 
    @NotBlank(message = "Position is required")
    private String position;

    // TODO: employee number 

    // salary field 
    @NotBlank(message = "Salary is required")
    private String salary;

    // status field 
    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    
    // TODO: department field

    // TODO: user field

    // date of hire field 
    @NotNull(message = "Date of hire is required")
    private Date hiredDate;

    // ---- getters and setters ----
    
}
