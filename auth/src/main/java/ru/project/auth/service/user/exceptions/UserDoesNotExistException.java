package ru.project.auth.service.user.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserDoesNotExistException extends Exception {

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
