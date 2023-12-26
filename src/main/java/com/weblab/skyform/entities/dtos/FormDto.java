package com.weblab.skyform.entities.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class FormDto {

    private int formCreatorId;

    private int eventId;

    private String name;

    private String description;

    private Date creationDate;

    private Date startDate;

    private Date endDate;
}
