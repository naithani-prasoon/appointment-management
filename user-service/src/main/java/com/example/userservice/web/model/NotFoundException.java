package com.example.userservice.web.model;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
