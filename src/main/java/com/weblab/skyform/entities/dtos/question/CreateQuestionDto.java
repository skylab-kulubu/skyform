package com.weblab.skyform.entities.dtos.question;

import com.weblab.skyform.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDto {

    private String questionText;

    private String questionType;

    private int questionOrder;

    private int maxRating;

    private boolean required;

    public Question buildQuestion(){
        Question question;
        if (this.questionType.equals(QuestionType.TYPE_RATING.getValue())){
            question = new QuestionRating(this.maxRating);
        } else  if (this.questionType.equals(QuestionType.TYPE_TEXT.getValue())){
            question = new QuestionText(questionText);
        } else if (this.questionType.equals(QuestionType.TYPE_DATE.getValue())){
            question = new QuestionDate(questionText);
        } else {
            throw new IllegalArgumentException("Question type is not valid");
        }

        question.setQuestionText(this.questionText);
        question.setQuestionOrder(this.questionOrder);
        question.setRequired(this.required);
        return question;
    }

}
