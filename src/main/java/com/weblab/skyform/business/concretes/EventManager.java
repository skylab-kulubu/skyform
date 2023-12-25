package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.constants.Messages;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.core.utilities.result.SuccessDataResult;
import com.weblab.skyform.core.utilities.result.SuccessResult;
import com.weblab.skyform.dataAccess.abstracts.EventDao;
import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventManager implements EventService {

    private EventDao eventDao;

    @Autowired
    public EventManager(EventDao eventDao){
        this.eventDao = eventDao;
    }


    @Override
    public Result addEvent(Event event) {
        eventDao.save(event);
        return new SuccessResult(Messages.eventAddSuccess);
    }

    @Override
    public DataResult<Event> getEventByEventId(int eventId) {
        var event = eventDao.findEventById(eventId);
        return new SuccessDataResult<Event>(event, Messages.eventBroughtByIdSuccess);
    }

    @Override
    public DataResult<List<Event>> getEvents() {
        var result = eventDao.findAll();
        return new SuccessDataResult<List<Event>>(result, Messages.eventsBroughtSuccess);
    }
}
