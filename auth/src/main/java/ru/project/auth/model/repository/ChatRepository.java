package ru.project.auth.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.auth.model.entity.Chat;
import ru.project.auth.model.entity.User;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
