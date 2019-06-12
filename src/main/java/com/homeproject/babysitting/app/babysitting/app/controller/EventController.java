package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    private EventService eventService;

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
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ids")
    public ResponseEntity findAllEventNames() {
        try {
            return ResponseEntity.ok(eventService.findAllNames());
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting eventnames data");
        }
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(eventService.create(request));
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> request) {
        try {
            return ResponseEntity.ok(eventService.update(request));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest()
                    .body("Attribute 'id' cannot be empty or null!");
        }
    }
}
