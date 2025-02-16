package eu.sadrian.quizshift.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
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

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }
}