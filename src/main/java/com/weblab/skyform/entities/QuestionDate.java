package com.weblab.skyform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "date_questions")
public class QuestionDate extends Question{

    public QuestionDate() {
        setQuestionType(QuestionType.TYPE_DATE);
    }

    public QuestionDate(String questionText) {
        this();
        setQuestionText(questionText);
    }
}
