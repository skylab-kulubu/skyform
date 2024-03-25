package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.constants.EventMessages;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.ErrorDataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.dataAccess.abstracts.EventDao;
import com.weblab.skyform.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventManager implements EventService {

    @Autowired
    private EventDao eventDao;

    @Override
    public DataResult<Event> getById(int id) {
       var result = eventDao.findById(id);

       if (result == null)
           return new ErrorDataResult<Event>(null, EventMessages.getByIdFail);

       return new SuccessDataResult<Event>(result, EventMessages.getByIdSuccess);
    }

    @Override
    public Result add(Event event) {
        eventDao.save(event);
        return new SuccessDataResult<Event>(event, EventMessages.addSuccess);
    }
}
