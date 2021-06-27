package ru.project.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.project.auth.controllers.userController.dto.UserResponseDTO;

import javax.persistence.*;

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

    @OneToOne(cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private UserCredentials credentials;

    public UserResponseDTO toDTO() {
        return new UserResponseDTO(
                userDetails.getId(),
                credentials.getLogin(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getRole());
    }
}
