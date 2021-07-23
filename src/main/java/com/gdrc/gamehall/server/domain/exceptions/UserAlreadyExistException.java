package com.gdrc.gamehall.server.domain.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException() {
        super("User already exists");
    }
}
