package eu.sadrian.quizshift.controller;

import eu.sadrian.quizshift.dto.LoginDTO;
import eu.sadrian.quizshift.model.Role;
import eu.sadrian.quizshift.model.User;
import eu.sadrian.quizshift.service.AuthService;
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
     * Handles user authentication by validating login credentials and
     * generating a JWT token upon successful authentication.
     *
     * @param loginDTO contains the user's login credentials (username and password).
     * @return a ResponseEntity containing the JWT token if authentication is successful,
     * or a 401 Unauthorized response with an error message if authentication fails.
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
