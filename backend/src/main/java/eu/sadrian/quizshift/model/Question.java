package eu.sadrian.quizshift.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;

    @ElementCollection
    private List<String> options;

    private int correctAnswerIndex;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}
