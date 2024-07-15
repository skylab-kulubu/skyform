package com.weblab.skyform.entities.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateResponseDto {

    private int questionId;

    private String responseType;

    private String textResponse;

    private int ratingResponse;

    private Date dateResponse;


}
