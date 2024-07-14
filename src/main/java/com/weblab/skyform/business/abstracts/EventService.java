package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.dtos.event.CreateEventDto;
import com.weblab.skyform.entities.dtos.event.GetEventDto;

import javax.xml.crypto.Data;
import java.util.List;

public interface EventService {

    Result addEvent(CreateEventDto createEventDto);

    DataResult<Event> getEventById(int id);

    DataResult<GetEventDto> getEventDtoById(int id);

    DataResult<List<Event>> getAllEvents(int id);

    DataResult<List<GetEventDto>> getAllEventsDto();
}
