package ru.project.auth.controllers.chatController;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.auth.controllers.chatController.dto.ChatCreationDTO;
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
    public ResponseEntity<List<Chat>> getChats(@RequestParam("userId") Long userId) {
        val current = userRepository.findById(userId);
        if (current.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (current.get().getUserDetails().getRole().equals(Role.ADMIN)) {
            return ResponseEntity.ok(chatRepository.findAll());
        }

        return ResponseEntity.ok(chatRepository.findAll().stream()
                .filter(chat -> chat.getUsers().contains(current.get()))
                .collect(Collectors.toList()));
    }

    @SneakyThrows
    @PostMapping("/")
    public ResponseEntity<?> addChat(@RequestBody ChatCreationDTO chatCreationDTO,
                                     @RequestParam("userId") Long userId) {
        log.info("Chat creation: " + chatCreationDTO.getName());

        val isAuthorized = userService.isAuthorized(userId);
        if (isAuthorized != null) {
            return isAuthorized;
        }

        var chat = chatRepository.saveAndFlush(Chat.builder().name(chatCreationDTO.getName()).users(List.of()).build());
        return ResponseEntity.ok(chat.getId());
    }

    @SneakyThrows
    @PostMapping("/{id}/{userId}")
    public ResponseEntity<?> addUser(@PathVariable("id") Long id,
                                     @PathVariable("userId") Long addId,
                                     @RequestParam("userId") Long userId) {
        log.info("Add user: " + addId + " to " + id);

        val isAdmin = userService.isAdmin(userId);
        if (isAdmin != null) {
            return isAdmin;
        }

        Optional<Chat> chat = chatRepository.findById(id);
        if (chat.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<User> user = userRepository.findById(addId);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var foundChat = chat.get();
        foundChat.getUsers().add(user.get());
        chatRepository.saveAndFlush(foundChat);

        return ResponseEntity.ok().build();
    }
}
