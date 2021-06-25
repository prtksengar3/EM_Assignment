package com.example.assignment.controller;

import com.example.assignment.exception.ResourceNotFoundException;
import com.example.assignment.model.User;
import com.example.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user)
    {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not exist with id : "+id));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not exist with id : "+id));
        user.setName(userDetails.getName());
        user.setEmailId(userDetails.getEmailId());
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
