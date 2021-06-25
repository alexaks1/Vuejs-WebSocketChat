package ru.project.auth.controllers.userController.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRegisterDTO {
    private final CredentialsDTO credentialsDTO;
    private final DetailsDTO detailsDTO;
}
