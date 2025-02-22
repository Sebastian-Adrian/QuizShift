package eu.sadrian.quizshift.security;

import eu.sadrian.quizshift.repository.TokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final TokenRepository tokenRepository;

    public SecurityConfig(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5174"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public OAuthFilter OAuthFilter() {
        return new OAuthFilter(this.tokenRepository);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Aktiviert die CORS-Konfiguration
                .cors(corsConfigurer -> corsConfigurer.configurationSource(corsConfigurationSource()))
                // Deaktiviert CSRF, falls nicht benötigt
                .csrf(AbstractHttpConfigurer::disable)
                // Anonyme Authentifizierung deaktivieren
                .anonymous(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                // Erlaube Registrierung und Login ohne Token
                                .requestMatchers(
                                        "/api/auth/register",
                                        "/api/auth/login",
                                        "/error",
                                        "/api/token/verify").permitAll()
                                // Alle anderen Endpunkte benötigen Authentifizierung
                                .anyRequest().authenticated()
                )
                // Eigener Filter für Token-Prüfung
                .addFilterBefore(
                        OAuthFilter(),
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}