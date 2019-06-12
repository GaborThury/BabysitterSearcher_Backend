package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private EventService eventService;
    private static final String EVENT_NAME_KEY = "name";

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(eventService.findAll());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ids")
    public ResponseEntity findAllEventNames() {
        try {
            return ResponseEntity.ok(eventService.findAllNames());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting eventnames data");
        }
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> map) {
        if (eventNameExistsAndNotEmpty(map)) {
            String name = map.get(EVENT_NAME_KEY).toString();
            return ResponseEntity.ok(eventService.create(name, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'name' cannot be empty or null!");
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> map) {
        if (eventNameExistsAndNotEmpty(map)) {
            String name = map.get(EVENT_NAME_KEY).toString();
            return ResponseEntity.ok(eventService.update(name, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'name' cannot be empty or null!");
        }
    }

    private boolean eventNameExistsAndNotEmpty(Map<String, Object> map) {
        try {
            String name = map.get(EVENT_NAME_KEY).toString();
            if (name.isEmpty()) throw new IllegalArgumentException();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
