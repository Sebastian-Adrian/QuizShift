package eu.sadrian.quizshift.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "option")
@Getter
@Setter
public class Option {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
