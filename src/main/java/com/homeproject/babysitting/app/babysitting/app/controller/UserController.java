package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private FirestoreService firestoreService;

/*
    @Autowired
    private UserService userService;
*/

    @GetMapping("/")
    public ResponseEntity getAllValues() {
        return ResponseEntity.ok(firestoreService.getDataFromFirestore());
    }

    @PostMapping("/")
    public ResponseEntity upload(@RequestBody Map<String, Object> map) {
        return ResponseEntity.ok(firestoreService.saveToFireStore(map));
    }

/*    @PostMapping("/user")
    public void save(@RequestHeader(value = "ID-TOKEN") String idToken) {
        userService.fakeSaveUser(idToken);
    }*/

}

