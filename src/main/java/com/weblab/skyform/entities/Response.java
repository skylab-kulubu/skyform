package com.weblab.skyform.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @ManyToOne
    @JoinColumn(name = "responder_id", referencedColumnName = "id")
    private User responder;


    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column(name = "response_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType responseType;

}
