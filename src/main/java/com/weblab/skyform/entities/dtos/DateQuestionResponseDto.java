package com.weblab.skyform.entities.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class DateQuestionResponseDto {

    private int responderId;

    private int questionId;

    private Date date;

}
