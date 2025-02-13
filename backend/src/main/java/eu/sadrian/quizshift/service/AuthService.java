package eu.sadrian.quizshift.service;

import eu.sadrian.quizshift.dto.LoginDTO;
import eu.sadrian.quizshift.dto.RegisterDTO;
import eu.sadrian.quizshift.model.Role;
import eu.sadrian.quizshift.model.User;
import eu.sadrian.quizshift.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password, Role role) {
        return null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("User not found")
        );
    }

    public String login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        //token erstellen
        // 1 day validity
        // Replace "secret-key" with a secure key
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day validity
                .signWith(SignatureAlgorithm.HS512, "secret-key") // Replace "secret-key" with a secure key
                .compact();

    }
}