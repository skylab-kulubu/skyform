package com.weblab.skyform.entities.dtos.event;

import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.dtos.form.GetFormDto;
import com.weblab.skyform.entities.dtos.user.GetUserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetEventDto {

    private int id;

    private String name;

    private String description;

    private GetUserDto eventCreator;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private List<GetFormDto> forms;

    public GetEventDto(Event event){
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.eventCreator = new GetUserDto(event.getCreator());
        this.creationDate = event.getCreationDate();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.forms = new GetFormDto().buildListGetFormDto(event.getForms());
    }

    public List<GetEventDto> buildListGetEventDto(List<Event> events){
        List<GetEventDto> listGetEventDto = new ArrayList<>();
        for (Event event : events) {
            listGetEventDto.add(new GetEventDto(event));
        }
        return listGetEventDto;
    }


}
