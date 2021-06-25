package ru.project.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.auth.model.entity.ChatMessage;

@Repository
public interface MessageRepository extends JpaRepository<ChatMessage, Long> {
}
