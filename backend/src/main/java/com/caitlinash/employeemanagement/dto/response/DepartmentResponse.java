package com.caitlinash.employeemanagement.dto.response;

import java.time.LocalDateTime;

public class DepartmentResponse {

    // ---- fields included ----

    private long id;
    private String name;
    private String description;
    private String location;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // ---- default constructor ---- 

    public DepartmentResponse() {}

    public DepartmentResponse(long id, String name, String description, String location,
                             LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    // ---- security method ----

    // toString() method
    @Override
    public String toString() {
        return "DepartmentResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' + 
                ", description='" + description + '\'' + 
                ", location='" + location + '\'' + 
                ", createdDate='" + createdDate + '\'' + 
                ", updatedDate='" + updatedDate + '\'' + 
                '}';
    }
}
