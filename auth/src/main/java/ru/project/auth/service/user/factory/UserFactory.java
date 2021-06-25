package ru.project.auth.service.user.factory;

import ru.project.auth.controllers.userController.dto.UserRegisterDTO;
import ru.project.auth.model.entity.User;

public interface UserFactory {
    User createUser(UserRegisterDTO registerDTO);
}
