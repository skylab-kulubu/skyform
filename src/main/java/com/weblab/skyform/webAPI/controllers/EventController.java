package com.weblab.skyform.webAPI.controllers;

import com.weblab.skyform.business.abstracts.EventService;
import com.weblab.skyform.core.utilities.results.Result;
import com.weblab.skyform.entities.dtos.event.CreateEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/addEvent")
    public ResponseEntity<Result> addEvent(@RequestBody CreateEventDto createEventDto) {
        var result = eventService.addEvent(createEventDto);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

   @GetMapping("/getEventById/{id}")
    public ResponseEntity<Result> getEventById(@PathVariable int id) {
        var result = eventService.getEventDtoById(id);

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<Result> getAllEvents() {
        var result = eventService.getAllEventsDto();

        if(result.isSuccess()) {
            return ResponseEntity.ok(result);
        }

        return ResponseEntity.badRequest().body(result);
    }


}
