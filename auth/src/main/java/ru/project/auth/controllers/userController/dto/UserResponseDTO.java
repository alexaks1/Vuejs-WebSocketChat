package ru.project.auth.controllers.userController.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserResponseDTO {
    private final DetailsDTO detailsDTO;
}
