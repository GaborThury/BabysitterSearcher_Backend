package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/ads")
@CrossOrigin(origins = "http://localhost:3000")
public class AdvertisementController {

    private AdvertisementService advertisementService;

    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(advertisementService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(advertisementService.findAll());
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ids")
    public ResponseEntity findIds() {
        try {
            return ResponseEntity.ok(advertisementService.findAllNames());
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting advertisement id list.");
        }
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> request) {
        return ResponseEntity.ok(advertisementService.create(request));
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> request) {
        try {
            return ResponseEntity.ok(advertisementService.update(request));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest()
                    .body("Attribute 'id' cannot be empty or null!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        try {
            advertisementService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/")
    public ResponseEntity deleteFields(@RequestBody Map<String, Object> request) {
        try {
            advertisementService.deleteFields(request);
            return ResponseEntity.ok().build();
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
