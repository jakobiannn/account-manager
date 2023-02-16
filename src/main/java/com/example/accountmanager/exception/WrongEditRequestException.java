package com.example.accountmanager.exception;

public class WrongEditRequestException extends Exception {
    public WrongEditRequestException() {
        super("[ERROR] Request violate API contract");
    }
}
