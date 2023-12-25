package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.core.utilities.result.DataResult;
import com.weblab.skyform.core.utilities.result.Result;
import com.weblab.skyform.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventsController {

    private EventService eventService;

    @Autowired
    public EventsController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping("/addevent")
    public Result addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @GetMapping("/getevents")
    public DataResult<List<Event>> getEvents(){
        return eventService.getEvents();
    }



}
