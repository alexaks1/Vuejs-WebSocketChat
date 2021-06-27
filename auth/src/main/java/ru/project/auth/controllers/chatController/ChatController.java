package ru.project.auth.controllers.chatController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.auth.controllers.chatController.dto.ChatCreationDTO;
import ru.project.auth.controllers.userController.dto.CredentialsDTO;
import ru.project.auth.model.entity.Chat;
import ru.project.auth.model.entity.Role;
import ru.project.auth.model.entity.User;
import ru.project.auth.model.repository.ChatRepository;
import ru.project.auth.model.repository.UserRepository;
import ru.project.auth.service.user.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/chats")
@RestController
public class ChatController {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @SneakyThrows
    @GetMapping("/")
    public ResponseEntity<List<Chat>> getChats(@RequestParam String login, @RequestParam String password) {
        final User user = userService.authenticate(new CredentialsDTO(login, password));

        if (user.getUserDetails().getRole().equals(Role.ADMIN)) {
            return ResponseEntity.ok(chatRepository.findAll());
        }

        return ResponseEntity.ok(chatRepository.findAll().stream()
                .filter(chat -> chat.getUsers().contains(user))
                .collect(Collectors.toList()));
    }

    @SneakyThrows
    @PostMapping("/")
    public ResponseEntity<?> addChat(@RequestBody ChatCreationDTO chatCreationDTO) {
        log.info("Chat creation: " + chatCreationDTO.getName());

        userService.authenticate(chatCreationDTO.getCredentialsDTO());
        var chat = chatRepository.saveAndFlush(Chat.builder()
                .name(chatCreationDTO.getName())
                .users(List.of())
                .build());
        return ResponseEntity.ok(chat.getId());
    }

    @SneakyThrows
    @PostMapping("/{id}/{userId}")
    public ResponseEntity<?> addUser(@PathVariable("id") Long id,
                                     @PathVariable("userId") Long addId,
                                     @RequestBody CredentialsDTO credentialsDTO) {
        log.info("Add user: " + addId + " to " + id);

        final User user = userService.authenticate(credentialsDTO);
        if (!userService.isAdmin(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> addUser = userRepository.findById(addId);
        if (addUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var foundChat = chat.get();
        foundChat.getUsers().add(addUser.get());
        chatRepository.saveAndFlush(foundChat);

        return ResponseEntity.ok().build();
    }
}
