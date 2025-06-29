package com.caitlinash.employeemanagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "departments",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"name"}
        )
    }
    )
public class Department {

    // ---- data fields ---- 

    // id field 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departments_seq_gen")
    @SequenceGenerator(name = "departments_seq_gen", sequenceName = "departments_seq", allocationSize = 1)
    private long id;

    // name field 
    @NotBlank(message = "Name is required")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // description field
    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;

    // location field 
    @NotBlank(message = "Location is required")
    private String location;

    // created date field 
    private LocalDateTime createdDate;

    // updated date field 
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

    public Department() {}

    public Department(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    // get id 
    public long getId() {
        return id;
    }

    // set id 
    public void setId(long id) {
        this.id = id;
    }

    // get name 
    public String getName() {
        return name;
    }

    // set name 
    public void setName(String name) {
        this.name = name;
    }

    // get description 
    public String getDescription() {
        return description;
    }

    // set description 
    public void setDescription(String description) {
        this.description = description;
    }

    // get location 
    public String getLocation() {
        return location;
    }

    // set location 
    public void setLocation(String location) {
        this.location = location;
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
