package com.weblab.skyform.entities.dtos.response;

import com.weblab.skyform.entities.ResponseDate;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseDateDto extends GetResponseDto{

    private Date date;

    public GetResponseDateDto(ResponseDate responseDate){
        super(responseDate.getId(),
                        responseDate.getResponder() == null ? null : new GetUserDto(responseDate.getResponder()),
                     new GetQuestionDto(responseDate.getQuestion()),
                     responseDate.getResponseType().getValue(),
                    responseDate.getResponseSession());
        ;
             this.date = responseDate.getDate();
    }
}
