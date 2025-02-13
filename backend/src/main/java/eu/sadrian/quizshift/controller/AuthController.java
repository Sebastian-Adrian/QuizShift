package eu.sadrian.quizshift.controller;

import eu.sadrian.quizshift.dto.LoginDTO;
import eu.sadrian.quizshift.model.Role;
import eu.sadrian.quizshift.model.User;
import eu.sadrian.quizshift.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginDTO) {
        String token = authService.login(loginDTO);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    

}
