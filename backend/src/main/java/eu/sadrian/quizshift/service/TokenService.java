package eu.sadrian.quizshift.service;

import eu.sadrian.quizshift.model.Token;
import eu.sadrian.quizshift.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public boolean verifyToken(String token) {
        Optional<Token> tokenObj = tokenRepository.findByTokenValue(token);

        return tokenObj.isPresent() && tokenObj.get().getExpiration().isAfter(java.time.LocalDateTime.now());
    }
}
