package com.weblab.skyform.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OptionQuestionResponseDto {
    private int id;
    private int questionId;
    private int responderId;
    private int selectedOptionId;

}
