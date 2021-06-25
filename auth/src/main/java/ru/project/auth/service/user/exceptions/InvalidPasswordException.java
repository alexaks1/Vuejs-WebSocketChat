package ru.project.auth.service.user.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
