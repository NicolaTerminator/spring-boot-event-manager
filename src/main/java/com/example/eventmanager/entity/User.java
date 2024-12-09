package com.example.eventmanager.entity;

import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=5, max = 20, message = "The username should contain a minimum of 5 characters and a maximum of 20 characters")
    @Schema(description = "User username", example = "")
    private String username;

    @NotBlank
    @Size(min=5, max = 50, message = "The email should contain a minimum of 5 characters and a maximum of 50 characters")
    @Email
    @Schema(description = "User email", example = "user@ibee.com")
    private String email;

    @NotBlank
    @Size(min = 5, max = 120, message = "The password should contain a minimum of 5 characters and a maximum of 120 characters")
    @Schema(description = "User password", example = "")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


}
