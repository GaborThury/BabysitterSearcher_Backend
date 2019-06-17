package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
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
    public ResponseEntity findOne(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(advertisementService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity findAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(advertisementService.findAll());
    }

    @GetMapping("/ids")
    public ResponseEntity findIds() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(advertisementService.findIdS());
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        advertisementService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        advertisementService.update(request);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {
        advertisementService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity deleteFields(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        advertisementService.deleteFields(request);
        return ResponseEntity.ok().build();
    }
}
