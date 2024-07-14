package com.weblab.skyform.entities.dtos.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateQuestionDto {

    private int formId;

    private String questionText;

    private String questionType;

    private int questionOrder;

    private int maxRating;

}
