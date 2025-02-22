package eu.sadrian.quizshift.controller;

import eu.sadrian.quizshift.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/verify")
    public boolean verifyToken(@RequestBody Map<String, String> token) {
        return tokenService.verifyToken(token.get("token"));
    }
}
