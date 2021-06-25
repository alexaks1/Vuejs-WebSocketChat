package ru.project.auth.controllers.userController;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.project.auth.service.user.exceptions.InvalidPasswordException;
import ru.project.auth.service.user.exceptions.UserAlreadyExistException;
import ru.project.auth.service.user.exceptions.UserDoesNotExistException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Void> invalidPasswordHandler() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Void> UserAlreadyExistHandler() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }


    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<Void> UserDoesNotExistHandler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> ExceptionHandler(Exception e) {
        e.printStackTrace();
        e.getCause();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
