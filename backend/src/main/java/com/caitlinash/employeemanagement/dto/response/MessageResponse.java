package com.caitlinash.employeemanagement.dto.response;

// MessageResponse - simple response for success/error messages
public class MessageResponse {

    // ---- included field ----

    private String message;

    // ---- default constructor ----

    public MessageResponse(String message) {
        this.message = message;
    }

    // ---- getter and setter

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
