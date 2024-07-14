package com.weblab.skyform.business.abstracts;

import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.Event;

public interface EventService {

    DataResult<Event> getEventById(int id);

    Result addEvent(Event event);
}
