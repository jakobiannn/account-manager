package com.example.accountmanager.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String login) {
        super("[ERROR] User " + login + " does not exist.");
    }
}
