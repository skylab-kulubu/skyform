package com.weblab.skyform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "text_questions")
public class QuestionText extends Question{

    public QuestionText() {
        setQuestionType(QuestionType.TYPE_TEXT);
    }

    public QuestionText(String questionText) {
        this();
        setQuestionText(questionText);
    }
}
