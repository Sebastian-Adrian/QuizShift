package eu.sadrian.quizshift.repository;

import eu.sadrian.quizshift.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.desktop.QuitEvent;
import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByTitleContainingIgnoreCase(String title);
}
