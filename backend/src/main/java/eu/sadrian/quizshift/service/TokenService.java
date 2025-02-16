package eu.sadrian.quizshift.service;

import eu.sadrian.quizshift.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String generateToken() {
        return null;
    }

    public boolean validateToken(String token) {
        return false;
    }

    public String getUsernameFromToken(String token) {
        return null;
    }

    public String getRoleFromToken(String token) {
        return null;
    }

    public String refreshToken(String token) {
        return null;
    }

    public void deleteToken(String token) {

    }


}
