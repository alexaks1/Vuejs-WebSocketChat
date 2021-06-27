package ru.project.auth.controllers.chatController.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.auth.controllers.userController.dto.CredentialsDTO;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ChatCreationDTO {
    CredentialsDTO credentialsDTO;
    String name;
}
