package com.weblab.skyform.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FormDto {

    private int formCreatorId;

    private String name;

    private String description;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private int eventId;

}
