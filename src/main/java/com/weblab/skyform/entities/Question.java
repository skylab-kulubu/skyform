package com.weblab.skyform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private Form form;

    @Column(name = "question_text")
    private String questionText;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_type_id")
    private QuestionType questionType;

    @Column(name = "question_order")
    private int questionOrder;


    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<QuestionOption> questionOptions;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TextQuestionResponse> textQuestionResponses;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<DateQuestionResponse> dateQuestionResponses;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OptionQuestionResponse> optionQuestionResponses;


}
