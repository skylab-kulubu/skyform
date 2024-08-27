package com.weblab.skyform.entities.dtos.question;

import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.QuestionRating;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionDto {

    //FOR ONLY TEXT AND DATE

    private int id;

    //private GetUserDto creator;

    private String questionText;

    private String questionType;

    private int questionOrder;

    private boolean required;

    private int formId;

    public GetQuestionDto(Question question){
       this.id = question.getId();
       //this.creator = new GetUserDto(question.getCreator());
       this.questionText = question.getQuestionText();
       this.questionType = question.getQuestionType().getValue();
       this.questionOrder = question.getQuestionOrder();
       this.required = question.isRequired();
       this.formId = question.getForm().getId();
    }

    public List<GetQuestionDto> buildListGetQuestionDto(List<Question> questions){
        List<GetQuestionDto> listGetQuestionDto = new ArrayList<>();
        for (Question question : questions) {
            if(question.getQuestionType().getValue().equals("RATING")){
                listGetQuestionDto.add(new GetQuestionRatingDto((QuestionRating) question));
            }else if (question.getQuestionType().getValue().equals("DATE") || question.getQuestionType().getValue().equals("TEXT")){
                listGetQuestionDto.add(new GetQuestionDto(question));
            }

        }
        return listGetQuestionDto;
    }
}
