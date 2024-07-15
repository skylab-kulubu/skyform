package com.weblab.skyform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rating_responses")
public class ResponseRating extends Response{

    private int rating;

    public ResponseRating() {
        setResponseType(QuestionType.TYPE_RATING);
    }

    public ResponseRating(int rating) {
        this();
       this.rating = rating;
    }
}
