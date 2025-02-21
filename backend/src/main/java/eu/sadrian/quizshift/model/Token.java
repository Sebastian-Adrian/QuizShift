package eu.sadrian.quizshift.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "token")
public class Token {

    @Id
    private String tokenValue;
    private String username;
    private String role;
    private LocalDateTime expiration;

    public Token() {
    }

    public Token(String tokenValue, String username, String role, LocalDateTime expiration) {
        this.tokenValue = tokenValue;
        this.username = username;
        this.role = role;
        this.expiration = expiration;
    }

}