package eu.sadrian.quizshift.repository;

import eu.sadrian.quizshift.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT q FROM Quiz q " +
            "LEFT JOIN FETCH q.questionsList questions " +
            "LEFT JOIN FETCH questions.antworten")
    List<Quiz> findAllWithDetails();
}
