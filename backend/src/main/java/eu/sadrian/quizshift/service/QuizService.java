package eu.sadrian.quizshift.service;

import eu.sadrian.quizshift.model.Quiz;
import eu.sadrian.quizshift.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public List<Quiz> searchQuizzesByTitle(String title) {
        return quizRepository.findByTitleContainingIgnoreCase(title);
    }

    public Quiz createQuiz(Quiz quiz) {
        return null;
    }

    public Quiz getQuizWithDetails(Long id) {
        return null;
    }

    public List<Quiz> getAllQuizzesWithDetails() {
        return quizRepository.getAllQuizzesWithDetails();
    }
}
