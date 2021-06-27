package ru.project.auth.controllers.userController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.auth.controllers.userController.dto.CredentialsDTO;
import ru.project.auth.controllers.userController.dto.UserRegisterDTO;
import ru.project.auth.controllers.userController.dto.UserResponseDTO;
import ru.project.auth.model.entity.User;
import ru.project.auth.model.repository.UserRepository;
import ru.project.auth.service.user.UserService;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @SneakyThrows
    @PostMapping("/sign-up")
    public ResponseEntity<UserResponseDTO> signUp(@RequestBody UserRegisterDTO registerDTO) {
        log.info("new user try to register, {}", registerDTO);
        final User user = userService.register(registerDTO);
        return ResponseEntity.ok(user.toDTO());
    }

    @SneakyThrows
    @PostMapping("/sign-in")
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody CredentialsDTO credentialsDTO) {
        log.info("new user try to sign-in");
        final User user = userService.authenticate(credentialsDTO);
        return ResponseEntity.ok(user.toDTO());
    }

    @SneakyThrows
    @GetMapping("/users")
    public ResponseEntity<?> getUsers(@RequestParam CredentialsDTO credentialsDTO) {
        final User user = userService.authenticate(credentialsDTO);

        if (!userService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        final List<User> userList = userRepository.findAll();
        return ResponseEntity.ok(userList.stream().map(User::toDTO));
    }
}
