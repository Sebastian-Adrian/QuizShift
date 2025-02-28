package eu.sadrian.quizshift.repository;

import eu.sadrian.quizshift.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByTitleContainingIgnoreCase(String title);

    @Query(value = """
            SELECT DISTINCT
              q.id AS id,
              q.title AS title,
              q.description AS description
            FROM quiz q
                  LEFT JOIN question que ON q.id = que.quiz_id
                  LEFT JOIN antwort a ON que.id = a.question_id
            """, nativeQuery = true)
    List<Quiz> getAllQuizzesWithDetails();
}
