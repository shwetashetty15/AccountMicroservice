package com.teckarch.AccountMS.demoAccountMS;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}
