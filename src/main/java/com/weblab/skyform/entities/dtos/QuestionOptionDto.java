package com.weblab.skyform.entities.dtos;

import lombok.Data;

@Data
public class QuestionOptionDto {

    private int questionId;

    private int optionOrder;

    private String optionText;


}
