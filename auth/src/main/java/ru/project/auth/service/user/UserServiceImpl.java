package ru.project.auth.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.project.auth.controllers.userController.dto.CredentialsDTO;
import ru.project.auth.controllers.userController.dto.UserRegisterDTO;
import ru.project.auth.model.entity.Role;
import ru.project.auth.model.entity.User;
import ru.project.auth.model.entity.UserCredentials;
import ru.project.auth.model.entity.UserDetails;
import ru.project.auth.model.repository.UserRepository;
import ru.project.auth.service.user.exceptions.InvalidPasswordException;
import ru.project.auth.service.user.exceptions.UserAlreadyExistException;
import ru.project.auth.service.user.exceptions.UserDoesNotExistException;
import ru.project.auth.service.user.factory.UserFactory;

import javax.annotation.PostConstruct;

@Log4j2
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @PostConstruct
    void init() {
        userRepository.saveAndFlush(new User(
                1,
                new UserDetails(1, "admin", "admin", Role.ADMIN),
                new UserCredentials(1, "admin", "admin")
        ));
    }

    @Override
    public User register(UserRegisterDTO userRegisterDTO) throws UserAlreadyExistException {
        log.info(userRegisterDTO);
        userRepository.findByCredentials_Login(userRegisterDTO.getCredentialsDTO().getLogin())
                .ifPresent((x) -> {
                    throw new UserAlreadyExistException();
                });

        final User user = userFactory.createUser(userRegisterDTO);
        log.info("new user registered!");
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User authenticate(CredentialsDTO credentialsDTO) throws UserDoesNotExistException, InvalidPasswordException {
        final User user = userRepository.findByCredentials_Login(credentialsDTO.getLogin())
                .orElseThrow(UserDoesNotExistException::new);

        if (!user.getCredentials().getPassword().equals(credentialsDTO.getPassword())) {
            log.info("user with login {} send invalid password. ", credentialsDTO.getPassword());
            throw new InvalidPasswordException();
        }

        log.info("User {} success auth", credentialsDTO.getLogin());
        return user;
    }

    @Override
    public boolean isAdmin(User user) {
        return user.getUserDetails().getRole().equals(Role.ADMIN);
    }
}
