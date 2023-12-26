package com.weblab.skyform.entities.dtos;

import lombok.Data;

@Data
public class QuestionDto {

    private int formId;

    private String questionText;

    private int questionTypeId;

    private int questionOrder;

}
