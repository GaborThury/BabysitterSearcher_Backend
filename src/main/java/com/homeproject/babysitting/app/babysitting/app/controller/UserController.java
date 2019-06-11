package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.FirestoreService;
import com.homeproject.babysitting.app.babysitting.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

/*
    @Autowired
    private FirestoreService firestoreService;
*/


    @Autowired
    private UserService userService;


    @GetMapping("/{name}")
    public ResponseEntity findUser(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }

    @GetMapping("/")
    public ResponseEntity findAll() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usernames")
    public ResponseEntity findAllUserNames() {
        try {
            return ResponseEntity.ok(userService.findAllUserNames());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody Map<String, Object> map) {
        if (userNameExistsAndNotEmpty(map)) {
            String userName = map.get("userName").toString();
            return ResponseEntity.ok(userService.create(userName, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'userName' cannot be empty or null!");
        }
    }

    @PutMapping("/")
    public ResponseEntity updateUser(@RequestBody Map<String, Object> map) {
        if (userNameExistsAndNotEmpty(map)) {
            String userName = map.get("userName").toString();
            return ResponseEntity.ok(userService.update(userName, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'userName' cannot be empty or null!");
        }
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

/*    @PostMapping("/user")
    public void create(@RequestHeader(value = "ID-TOKEN") String idToken) {
        userService.fakeSaveUser(idToken);
    }*/

}
