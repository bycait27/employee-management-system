package com.caitlinash.employeemanagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateDepartmentRequest {

    // ---- included fields ----

    @NotBlank(message = "Name is required")
    private String name;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
    
    @NotBlank(message = "Location is required")
    private String location;

        // ---- default constructor ----

    public UpdateDepartmentRequest() {}

    public UpdateDepartmentRequest(String name, String description, String location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    // ---- getters and setters ----

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

    // ---- security method ----

     // toString() method 
    @Override
    public String toString() {
        return "UpdateDepartmentRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
    
}
