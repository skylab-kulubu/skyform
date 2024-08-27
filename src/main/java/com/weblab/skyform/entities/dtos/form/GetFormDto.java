package com.weblab.skyform.entities.dtos.form;

import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.dtos.question.GetQuestionDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFormDto {

    private int id;

    private GetUserDto formCreator;

    private String name;

    private String description;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private List<GetQuestionDto> questions;


    public GetFormDto(Form form){
        this.id = form.getId();
        this.formCreator = new GetUserDto(form.getCreator());
        this.name = form.getName();
        this.description = form.getDescription();
        this.creationDate = form.getCreationDate();
        this.startDate = form.getStartDate();
        this.endDate = form.getEndDate();
        this.questions = new GetQuestionDto().buildListGetQuestionDto(form.getQuestions());
    }

    public List<GetFormDto> buildListGetFormDto(List<Form> forms){
        List<GetFormDto> listGetFormDto = new ArrayList<>();
        for (Form form : forms) {
            listGetFormDto.add(new GetFormDto(form));
        }
        return listGetFormDto;
    }


}
