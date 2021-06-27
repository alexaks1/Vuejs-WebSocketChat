package ru.project.auth.service.user;

import ru.project.auth.controllers.userController.dto.CredentialsDTO;
import ru.project.auth.controllers.userController.dto.UserRegisterDTO;
import ru.project.auth.model.entity.User;
import ru.project.auth.service.user.exceptions.InvalidPasswordException;
import ru.project.auth.service.user.exceptions.UserDoesNotExistException;

public interface UserService {
    User register(UserRegisterDTO userRegisterDTO);

    User authenticate(CredentialsDTO credentialsDTO) throws UserDoesNotExistException, InvalidPasswordException;

    boolean isAdmin(User user);
}
