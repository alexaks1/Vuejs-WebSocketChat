package ru.project.auth.controllers.chatController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.project.auth.model.entity.ChatMessage;
import ru.project.auth.model.repository.MessageRepository;

@Log4j2
@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WebSocketController {
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        messageRepository.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getChatId().toString(),
                "/messages",
                chatMessage);
    }

    @GetMapping("/messages/{chatId}")
    public ResponseEntity<?> getMessages(@PathVariable Long chatId) {
        return ResponseEntity.ok(messageRepository.findAllByChatId(chatId));
    }
}
