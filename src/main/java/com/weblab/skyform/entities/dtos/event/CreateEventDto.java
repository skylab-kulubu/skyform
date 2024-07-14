package com.weblab.skyform.entities.dtos.event;

import com.weblab.skyform.entities.Role;
import com.weblab.skyform.entities.User;
import com.weblab.skyform.entities.dtos.user.CreateUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

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
