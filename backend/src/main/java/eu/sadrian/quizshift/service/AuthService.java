package eu.sadrian.quizshift.service;

import eu.sadrian.quizshift.dto.LoginDTO;
import eu.sadrian.quizshift.model.Role;
import eu.sadrian.quizshift.model.User;
import eu.sadrian.quizshift.repository.TokenRepository; // Repository für Speicherung von Tokens
import eu.sadrian.quizshift.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, Role role) {
        return null; // User-Registrierungslogik hier implementieren
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("Benutzer nicht gefunden")
        );
    }

    /**
     * Diese Methode führt den Login durch und speichert das erzeugte Token in der Datenbank.
     * Sie ist mit @Transactional markiert, um sicherzustellen, dass alle Datenbankoperationen
     * im Rahmen einer Transaktion ausgeführt werden.
     */
    @Transactional
    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Falscher Benutzername oder Passwort"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Falscher Benutzername oder Passwort");
        }

        // Generiere das Opaque Token
        String opaqueToken = UUID.randomUUID().toString();

        // Token und zugehörige Daten speichern
        tokenRepository.saveToken(opaqueToken, user.getUsername(), user.getRole().name());

        // Das Token zurückgeben
        return opaqueToken;
    }
}