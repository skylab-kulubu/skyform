package com.weblab.skyform.entities.dtos.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.Form;
import com.weblab.skyform.entities.Question;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetFormDto {

    private int id;

    private GetUserDto creator;

    private String name;

    private String description;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private List<Question> questions;

    public GetFormDto buildGetFormDto(Form form){
        return GetFormDto.builder()
                .id(form.getId())
                .creator(new User().buildGetUserDto(form.getCreator()))
                .name(form.getName())
                .description(form.getDescription())
                .creationDate(form.getCreationDate())
                .startDate(form.getStartDate())
                .endDate(form.getEndDate())
                .questions(form.getQuestions())
                .build();
    }


}
