package eu.sadrian.quizshift.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "options")
@Getter
@Setter
public class Options {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String text;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
