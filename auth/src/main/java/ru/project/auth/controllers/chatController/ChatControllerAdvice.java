package ru.project.auth.controllers.chatController;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ChatControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> ExceptionHandler(Exception e) {
        e.printStackTrace();
        e.getCause();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
