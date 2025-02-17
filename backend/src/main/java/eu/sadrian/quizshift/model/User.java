package eu.sadrian.quizshift.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column()
    private int total_xp = 0;

    @Column()
    private int current_level = 1;

    @Column()
    private int total_coins = 0;

    @Setter
    @Column(unique = false, nullable = false)
    private Role role;

    @JsonIgnore
    private String password;

}
