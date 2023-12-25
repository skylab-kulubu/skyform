package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.Event;

import java.util.List;

public interface EventService {

    Result addEvent(Event event);

    DataResult<Event> getEventByEventId(int eventId);

    DataResult<List<Event>> getEvents();

}
