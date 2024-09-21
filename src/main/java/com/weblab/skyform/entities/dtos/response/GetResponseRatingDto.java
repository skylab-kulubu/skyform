package com.weblab.skyform.entities.dtos.response;

import com.weblab.skyform.entities.QuestionRating;
import com.weblab.skyform.entities.ResponseRating;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import com.weblab.skyform.entities.dtos.question.GetQuestionRatingDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseRatingDto extends GetResponseDto{

    private int rating;

    public GetResponseRatingDto(ResponseRating responseRating){
       super(responseRating.getId(),
               responseRating.getQuestion().getForm().getId(),
                responseRating.getResponder() == null ? null : new GetUserDto(responseRating.getResponder()),
                    new GetQuestionRatingDto((QuestionRating) responseRating.getQuestion()),
               responseRating.getResponseType().getValue(),
               responseRating.getResponseSession());
            this.rating = responseRating.getRating();
    }
}
