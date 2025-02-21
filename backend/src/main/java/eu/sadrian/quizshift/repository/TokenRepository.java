package eu.sadrian.quizshift.repository;

import eu.sadrian.quizshift.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenValue(String token);

    @Modifying
    @Query("INSERT INTO Token (tokenValue, username, role, expiration) " +
            "VALUES (:tokenValue, :username, :role, :expiration)")
    void saveToken(@Param("tokenValue") String tokenValue,
                   @Param("username") String username,
                   @Param("role") String role,
                   @Param("expiration") LocalDateTime expiration
    );
}


