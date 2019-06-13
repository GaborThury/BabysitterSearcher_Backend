package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

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
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usernames")
    public ResponseEntity findAllUserNames() {
        try {
            return ResponseEntity.ok(userService.findAllNames());
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while getting usernames data");
        }
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> request) {
        try {
            return ResponseEntity.ok(userService.create(request));
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Attribute 'userName' cannot be empty or null!");
        }
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> request) {
        try {
            return ResponseEntity.ok(userService.update(request));
        } catch (NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body("Attribute 'userName' cannot be empty or null!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/")
    public ResponseEntity deleteFields(@RequestBody Map<String, Object> request) {
        try {
            userService.deleteFields(request);
            return ResponseEntity.ok().build();
        } catch (ExecutionException | InterruptedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


/*    @PostMapping("/user")
    public void create(@RequestHeader(value = "ID-TOKEN") String idToken) {
        userService.fakeSaveUser(idToken);
    }*/

}
