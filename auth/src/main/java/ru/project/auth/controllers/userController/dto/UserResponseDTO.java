package ru.project.auth.controllers.userController.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.project.auth.model.entity.Role;


@AllArgsConstructor
@Data
public class UserResponseDTO {
    private final Number id;
    private final String login;
    private final String firstName;
    private final String lastName;
    private final Role role;
}
