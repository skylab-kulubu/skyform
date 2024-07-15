package com.weblab.skyform.entities.dtos.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEventDto {

    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

}
