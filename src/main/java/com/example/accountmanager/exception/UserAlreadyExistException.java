package com.example.accountmanager.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String login) {
        super("[ERROR] User " + login + " already exist.");
    }
}
