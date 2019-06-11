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
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{name}")
    public ResponseEntity findEvent(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(eventService.findEventByName(name));
    }

    @GetMapping("/")
    public ResponseEntity findAllEvents() {
        try {
            return ResponseEntity.ok(eventService.findAll());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/eventnames")
    public ResponseEntity findAllEventNames() {
        try {
            return ResponseEntity.ok(eventService.findAllEventNames());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting eventnames data");
        }
    }

    @PostMapping("/")
    public ResponseEntity createEvent(@RequestBody Map<String, Object> map) {
        if (eventNameExistsAndNotEmpty(map)) {
            String name = map.get("name").toString();
            return ResponseEntity.ok(eventService.create(name, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'name' cannot be empty or null!");
        }
    }

    @PutMapping("/")
    public ResponseEntity updateEvent(@RequestBody Map<String, Object> map) {
        if (eventNameExistsAndNotEmpty(map)) {
            String name = map.get("name").toString();
            return ResponseEntity.ok(eventService.update(name, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'name' cannot be empty or null!");
        }
    }

    private boolean eventNameExistsAndNotEmpty(Map<String, Object> map) {
        try {
            String name = map.get("name").toString();
            if (name.isEmpty()) throw new IllegalArgumentException();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
