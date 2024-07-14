package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.EventMessages;
import com.weblab.skyform.core.utilities.results.DataResult;
import com.weblab.skyform.core.utilities.results.ErrorDataResult;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.core.utilities.results.SuccessDataResult;
import com.weblab.skyform.dataAccess.abstracts.EventDao;
import com.weblab.skyform.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class EventManager implements EventService {

    private EventDao eventDao;

    private UserService userService;

    @Override
    public DataResult<Event> getEventById(int id) {
       var result = eventDao.findById(id);

       if (result == null)
           return new ErrorDataResult<Event>(EventMessages.getByIdFail);

       return new SuccessDataResult<Event>(result, EventMessages.getByIdSuccess);
    }

    @Override
    public Result addEvent(Event event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();



    }
}
