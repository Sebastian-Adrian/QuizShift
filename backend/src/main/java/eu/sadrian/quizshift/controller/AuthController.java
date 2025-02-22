package eu.sadrian.quizshift.controller;

import eu.sadrian.quizshift.dto.LoginDTO;
import eu.sadrian.quizshift.model.Role;
import eu.sadrian.quizshift.model.User;
import eu.sadrian.quizshift.service.AuthService;
import eu.sadrian.quizshift.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The AuthController class provides REST endpoints for authentication-related operations,
 * including user registration and login functionalities.
 * It acts as the entry point for handling authentication requests.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestParam String username,
                                         @RequestParam String password,
                                         @RequestParam Role role) {
        User user = authService.registerUser(username, password, role);
        return ResponseEntity.ok(user);
    }

    /**
     * Authenticates a user based on the provided login credentials.
     * If authentication is successful, a token is returned. If the credentials are invalid,
     * a 401 response is returned with an appropriate message.
     *
     * @param loginDTO the login data transfer object containing the username and password
     * @return a ResponseEntity containing the authentication token if successful,
     *         or a 401 status with an error message otherwise
     */
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Falscher Benutzername oder Passwort");
        }
    }
}
