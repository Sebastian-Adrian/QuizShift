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
        SELECT
          q.id AS quiz_Id,
          q.title AS quiz_Title,
          q.description AS quiz_Description,
          que.id AS question_Id,
          que.text AS question_Text,
          que.type AS question_Type,
          a.id AS answer_Id,
          a.text AS answer_Text,
          a.is_correct AS isAnswerCorrect
        FROM quiz q
              LEFT JOIN question que ON q.id = que.quiz_id
              LEFT JOIN antwort a ON que.id = a.question_id
        ORDER BY q.title, que.text, a.text
   \s""", nativeQuery = true)
    List<Quiz> getAllQuizzesWithDetails();
}
