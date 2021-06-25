package ru.project.auth.service.user.factory;

import org.springframework.stereotype.Component;
import ru.project.auth.controllers.userController.dto.UserRegisterDTO;
import ru.project.auth.model.entity.Role;
import ru.project.auth.model.entity.User;
import ru.project.auth.model.entity.UserCredentials;
import ru.project.auth.model.entity.UserDetails;

@Component
public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(UserRegisterDTO registerDTO) {
        return User.builder()
                .credentials(
                        UserCredentials.builder()
                                .login(registerDTO.getCredentialsDTO().getLogin())
                                .password(registerDTO.getCredentialsDTO().getPassword()) // need bcrypt
                                .build()
                )
                .userDetails(
                        UserDetails.builder()
                                .address(registerDTO.getDetailsDTO().getAddress())
                                .firstName(registerDTO.getDetailsDTO().getFirstName())
                                .lastName(registerDTO.getDetailsDTO().getLastName())
                                .role(Role.USER)
                                .build()
                )
                .build();
    }
}
