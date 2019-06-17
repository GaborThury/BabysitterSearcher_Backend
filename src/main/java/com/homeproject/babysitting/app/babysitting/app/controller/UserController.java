package com.homeproject.babysitting.app.babysitting.app.controller;

import com.homeproject.babysitting.app.babysitting.app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
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
    public ResponseEntity findOne(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity findAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/usernames")
    public ResponseEntity findAllUserNames()
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.findIdS());
    }

    @PostMapping("/")
    public ResponseEntity create(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        userService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/")
    public ResponseEntity update(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        userService.update(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") String id)
            throws ExecutionException, InterruptedException {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity deleteFields(@RequestBody Map<String, Object> request)
            throws ExecutionException, InterruptedException {
        userService.deleteFields(request);
        return ResponseEntity.ok().build();
    }


/*    @PostMapping("/user")
    public void create(@RequestHeader(value = "ID-TOKEN") String idToken) {
        userService.fakeSaveUser(idToken);
    }*/

}
