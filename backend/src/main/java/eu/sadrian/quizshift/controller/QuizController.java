package eu.sadrian.quizshift.controller;

import eu.sadrian.quizshift.model.Quiz;
import eu.sadrian.quizshift.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping()
    List<Quiz> all() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/all")
    public List<Quiz> getAllQuizzesWithDetails() {
        return quizService.getAllQuizzesWithDetails();
    }

    @GetMapping("/{id}")
    public Quiz getQuiz(@PathVariable Long id) {
        return quizService.getQuizWithDetails(id);
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }
}

