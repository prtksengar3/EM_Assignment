package com.example.assignment.controller;

import com.example.assignment.dto.UserDto;
import com.example.assignment.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
@Slf4j
@Validated
public class UserController {

    @Autowired private UserService userService;

    @Autowired private RestTemplate restTemplate;

    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                     @RequestParam(defaultValue = "id") String sortBy){

//        String token = this.restTemplate.getForObject("http://localhost:9090/token",String.class);

//        return userService.getAllUsers();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return userService.UserPageable(pageable);
    }

//


    @PostMapping("/users")
    public UserDto createUser(@RequestParam("data") String jsonString,@RequestParam("image") MultipartFile multipartFile) throws JsonProcessingException {
        UserDto userDto = new ObjectMapper().readValue(jsonString,UserDto.class);
        return userService.createUser(userDto,multipartFile);
    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }


    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,@Valid @RequestBody UserDto userDetails){
        UserDto userDto = userService.updateUser(id,userDetails);
        return ResponseEntity.ok(userDto);
    }
}
