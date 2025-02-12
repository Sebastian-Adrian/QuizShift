package eu.sadrian.quizshift.controller;

import eu.sadrian.quizshift.dto.LoginDTO;
import eu.sadrian.quizshift.dto.RegisterDTO;
import eu.sadrian.quizshift.model.User;
import eu.sadrian.quizshift.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO) {
        return ResponseEntity.ok(authService.registerUser(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }
}
