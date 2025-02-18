package eu.sadrian.quizshift.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "achievements")
@Getter
@Setter
public class Achievements {


    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, length = 50)
    private String code;

    @Column(length = 100)
    private String name;

    @Column
    private String description;

    @Column
    private int xp_reward = 0;

    @Column
    private int coins_reward = 0;
}
