package com.example.chat_web.controller;

import com.example.chat_web.model.User;
import com.example.chat_web.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private final UserServices userServices;

    @PostMapping
    public void register(User user) {
        userServices.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            User loggedInUser = userServices.login(user);
            System.out.println("Login exitoso, usuario: " + loggedInUser);
            return ResponseEntity.ok(loggedInUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/logout")
    public void logout(String email){
       userServices.logout(email);
    }


    @GetMapping
    public List<User> findAll(){
        return userServices.findAll();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>handle(Exception ex){
        ex.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }
}
