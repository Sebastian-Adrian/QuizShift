package eu.sadrian.quizshift.security;

import eu.sadrian.quizshift.model.Token;
import eu.sadrian.quizshift.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

public class OAuthFilter extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;

    public OAuthFilter(TokenRepository tokenRepository) {

        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization Header fehlt oder ist ungültig");
            return;
        }

        String tokenValue = authorizationHeader.substring(7);

        tokenRepository.findByTokenValue(tokenValue).ifPresentOrElse(token -> {
            if (token.getExpiration().isBefore(LocalDateTime.now())) {
                SecurityContextHolder.clearContext();
                try {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token ist abgelaufen");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                return;
            }

            // Benutzerkontext setzen
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(token.getUsername(), null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }, () -> {
            try {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Ungültiges Token");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Weiter zum nächsten Filter, falls alles gültig ist
        filterChain.doFilter(request, response);
    }
}