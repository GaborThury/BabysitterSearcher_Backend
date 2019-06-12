package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private static final String ADVERTISEMENT_NAME_KEY = "name";

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
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ids")
    public ResponseEntity findIds() {
        try {
            return ResponseEntity.ok(advertisementService.findAllNames());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting advertisement id list.");
        }
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> map) {
        return null;
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> map) {
        return null;
    }

    private boolean userNameExistsAndNotEmpty(Map<String, Object> map) {
        try {
            String userName = map.get("userName").toString();
            if (userName.isEmpty()) throw new IllegalArgumentException();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
