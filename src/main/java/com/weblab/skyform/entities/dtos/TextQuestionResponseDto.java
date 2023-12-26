package com.weblab.skyform.entities.dtos;

import lombok.Data;

@Data
public class TextQuestionResponseDto {

    private int responderId;

    private int questionId;

    private String text;


}
