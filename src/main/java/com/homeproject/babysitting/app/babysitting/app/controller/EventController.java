package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
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
    public ResponseEntity findById(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity findAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/ids")
    public ResponseEntity findAllIdS() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(eventService.findIdS());
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        eventService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        eventService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {
        eventService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity deleteFields(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        eventService.deleteFields(request);
        return ResponseEntity.ok().build();
    }
}
