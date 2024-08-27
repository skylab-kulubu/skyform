package com.weblab.skyform.business.concretes;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.business.abstracts.UserService;
import com.weblab.skyform.business.constants.EventMessages;
import com.weblab.skyform.core.utilities.results.*;
import com.weblab.skyform.dataAccess.abstracts.EventDao;
import com.weblab.skyform.entities.Event;
import com.weblab.skyform.entities.dtos.event.CreateEventDto;
import com.weblab.skyform.entities.dtos.event.GetEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventManager implements EventService {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private UserService userService;


    @Override
    public Result addEvent(CreateEventDto createEventDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserMail = authentication.getName();

        var userResult = userService.getUserByEmail(currentUserMail);

        if(!userResult.isSuccess()){
            return userResult;
        }

        var user = userResult.getData();


        var eventToSave = Event.builder()
                .name(createEventDto.getName())
                .description(createEventDto.getDescription())
                .creator(user)
                .creationDate(new Date())
                .startDate(createEventDto.getStartDate())
                .endDate(createEventDto.getEndDate())
                .build();

        eventDao.save(eventToSave);

        return new SuccessResult(EventMessages.eventAddedSuccessfully);
    }

    @Override
    public DataResult<Event> getEventById(int id) {
        var event = eventDao.findById(id);

        if(!event.isPresent()){
            return new ErrorDataResult<>(EventMessages.eventNotFound);
        }

        return new SuccessDataResult<>(event.get(), EventMessages.eventSuccessfullyBrought);
    }

    @Override
    public DataResult<GetEventDto> getEventDtoById(int id) {
        var event = eventDao.findById(id);

        if(!event.isPresent()){
            return new ErrorDataResult<>(EventMessages.eventNotFound);
        }

        var returnEvent = new GetEventDto(event.get());

        return new SuccessDataResult<>(returnEvent, EventMessages.eventSuccessfullyBrought);
    }

    @Override
    public DataResult<List<Event>> getAllEvents(int id) {
        var result = eventDao.findAll();

        if(result.isEmpty()) {
            return new ErrorDataResult<>(EventMessages.eventsNotFound);
        }

        return new SuccessDataResult<>(result, EventMessages.eventsSuccessfullyBrought);
    }

    @Override
    public DataResult<List<GetEventDto>> getAllEventsDto() {
        var eventList = eventDao.findAll();

        if(eventList.isEmpty()) {
            return new ErrorDataResult<>(EventMessages.eventsNotFound);
        }

        List<GetEventDto> returnList = new GetEventDto().buildListGetEventDto(eventList);

        return new SuccessDataResult<>(returnList, EventMessages.eventsSuccessfullyBrought);
    }
}
