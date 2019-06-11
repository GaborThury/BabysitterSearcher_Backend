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
public class UserController {

    @Autowired
    private FirestoreService firestoreService;


    @Autowired
    private UserService userService;


    @GetMapping("/{name}")
    public ResponseEntity findUser(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(userService.findUserByName(name));
    }

    @GetMapping("/")
    public ResponseEntity findall() {
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

    @PostMapping("/{name}")
    public ResponseEntity createUser(@PathVariable(value = "name") String name ,@RequestBody Map<String, Object> map) {
        return ResponseEntity.ok(userService.create(name, map));
    }

    @PutMapping("/{name}")
    public ResponseEntity updateUser(@PathVariable(value = "name") String name ,@RequestBody Map<String, Object> map) {
        return ResponseEntity.ok(userService.update(name, map));
    }


/*    @PostMapping("/user")
    public void create(@RequestHeader(value = "ID-TOKEN") String idToken) {
        userService.fakeSaveUser(idToken);
    }*/

}
