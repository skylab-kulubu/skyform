package com.weblab.skyform.entities.dtos;

import lombok.Data;

@Data
public class OptionQuestionResponseDto {

    private int responderId;

    private int questionId;

    private int chosenOptionId;

}
