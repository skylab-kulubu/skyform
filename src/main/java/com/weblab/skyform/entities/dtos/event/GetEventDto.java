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
@Builder
public class GetEventDto {

    private int id;

    private String name;

    private String description;

    private GetUserDto eventCreator;

    private Date creationDate;

    private Date startDate;

    private Date endDate;

    private List<GetFormDto> forms;

    public GetEventDto buildGetEventDto(Event event){
        return GetEventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .eventCreator(new GetUserDto().buildGetUserDto(event.getCreator()))
                .creationDate(event.getCreationDate())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .forms(new GetFormDto().buildListGetFormDto(event.getForms()))
                .build();
    }

    public List<GetEventDto> buildListGetEventDto(List<Event> events){
        List<GetEventDto> listGetEventDto = new ArrayList<>();
        for (Event event : events) {
            listGetEventDto.add(buildGetEventDto(event));
        }
        return listGetEventDto;
    }


}
