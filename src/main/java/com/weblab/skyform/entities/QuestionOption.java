package com.weblab.skyform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_options")
public class QuestionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "option_order")
    private int optionOrder;

    @Column(name = "option_text")
    private String optionText;


    @OneToMany(mappedBy = "chosenOption", fetch = FetchType.LAZY)
    private List<OptionQuestionResponse> optionQuestionResponses;




}
