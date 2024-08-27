package com.weblab.skyform.entities.dtos.question;

import com.weblab.skyform.entities.QuestionRating;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionRatingDto extends GetQuestionDto{

    //FOR ONLY RATING

    private int maxRating;

    public GetQuestionRatingDto(QuestionRating questionRating){
        super(questionRating.getId(),
                //new GetUserDto(questionRating.getCreator()),
                questionRating.getQuestionText(),
                questionRating.getQuestionType().getValue(),
                questionRating.getQuestionOrder(),
                questionRating.isRequired(),
                questionRating.getForm().getId());
        this.maxRating = questionRating.getMaxRating();

    }
}
