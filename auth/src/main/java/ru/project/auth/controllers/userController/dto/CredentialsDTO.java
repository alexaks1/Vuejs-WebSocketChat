package ru.project.auth.controllers.userController.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CredentialsDTO {
    private final String login;
    private final String password;
}
