package com.weblab.skyform.entities.dtos.response;

import com.weblab.skyform.entities.Response;
import com.weblab.skyform.entities.ResponseDate;
import com.weblab.skyform.entities.ResponseRating;
import com.weblab.skyform.entities.ResponseText;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetResponseDto {

    private int id;

    private GetUserDto responder;

    private GetQuestionDto question;

    private String responseType;

    public GetResponseDto(Response response){
        this.id = response.getId();
        this.responder = new GetUserDto(response.getResponder());
        this.question = new GetQuestionDto(response.getQuestion());
        this.responseType = response.getResponseType().getValue();
    }

    public List<GetResponseDto> buildListGetResponseDto(List<Response> responses){
        List<GetResponseDto> listGetResponseDto = new ArrayList<>();
        for (Response response : responses) {
            if(response.getResponseType().getValue().equals("RATING")) {
                listGetResponseDto.add(new GetResponseRatingDto((ResponseRating) response));
            }else if (response.getResponseType().getValue().equals("TEXT")){
                listGetResponseDto.add(new GetResponseTextDto((ResponseText) response));
            }else if (response.getResponseType().getValue().equals("DATE")) {
                listGetResponseDto.add(new GetResponseDateDto((ResponseDate) response));
            }
        }
        return listGetResponseDto;
    }

}
