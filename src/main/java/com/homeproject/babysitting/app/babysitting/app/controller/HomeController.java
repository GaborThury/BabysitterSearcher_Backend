package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private FirestoreService firestoreService;

    @GetMapping("/")
    public ResponseEntity getAllValues() {
        return ResponseEntity.ok(firestoreService.getQuoteFromFirestore());
    }

    @PostMapping("/")
    public ResponseEntity upload(@RequestBody Map<String, Object> map) {
        return ResponseEntity.ok(firestoreService.saveToFireStore(map));
    }
}

