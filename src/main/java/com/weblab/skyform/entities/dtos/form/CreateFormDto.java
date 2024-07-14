package com.weblab.skyform.entities.dtos.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateFormDto {

    //id

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private int eventId;

}
