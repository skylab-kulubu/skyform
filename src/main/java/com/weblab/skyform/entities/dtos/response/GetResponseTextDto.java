package com.weblab.skyform.entities.dtos.response;

import com.weblab.skyform.entities.ResponseText;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseTextDto extends GetResponseDto{

    private String text;

    public GetResponseTextDto(ResponseText responseText){
        super(responseText.getId(),
                     new GetUserDto(responseText.getResponder()),
                     new GetQuestionDto(responseText.getQuestion()),
                     responseText.getResponseType().getValue());
             this.text = responseText.getText();
    }
}
