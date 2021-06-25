package ru.project.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.auth.controllers.userController.dto.DetailsDTO;
import ru.project.auth.controllers.userController.dto.UserResponseDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "auth_users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_time", updatable = false)
    private LocalDateTime created;

    @Column(name = "modified_time")
    private LocalDateTime modified;

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private UserCredentials credentials;


    @PrePersist
    private void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    private void onUpdate() {
        this.setModified(LocalDateTime.now());
    }

    public UserResponseDTO toDTO() {
        return new UserResponseDTO(
                new DetailsDTO(
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getAddress(),
                        userDetails.getRole())
        );
    }

}
