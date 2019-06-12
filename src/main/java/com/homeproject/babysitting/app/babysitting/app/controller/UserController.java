package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.UserService;
import org.springframework.http.HttpStatus;
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

    private UserService userService;
    private static final String USERNAME_KEY = "userName";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(userService.findById(id));
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
            return ResponseEntity.ok(userService.findAllNames());
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting usernames data");
        }
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> map) {
        if (userNameExistsAndNotEmpty(map)) {
            String userName = map.get(USERNAME_KEY).toString();
            return ResponseEntity.ok(userService.create(userName, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'userName' cannot be empty or null!");
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> map) {
        if (userNameExistsAndNotEmpty(map)) {
            String userName = map.get(USERNAME_KEY).toString();
            return ResponseEntity.ok(userService.update(userName, map));
        } else {
            return ResponseEntity.badRequest().body("Attribute 'userName' cannot be empty or null!");
        }
    }

    private boolean userNameExistsAndNotEmpty(Map<String, Object> map) {
        try {
            String userName = map.get(USERNAME_KEY).toString();
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
