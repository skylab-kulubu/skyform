package com.weblab.skyform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rating_questions")
public class QuestionRating extends Question{
    private int maxRating;

    public QuestionRating() {
        setQuestionType(QuestionType.TYPE_RATING);
    }

    public QuestionRating(int maxRating) {
        this();
        this.maxRating = maxRating;
    }


}
