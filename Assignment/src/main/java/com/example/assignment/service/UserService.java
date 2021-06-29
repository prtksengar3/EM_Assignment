package com.example.assignment.service;

import com.example.assignment.exception.MyNotFoundException;
import com.example.assignment.exception.ResourceNotFoundException;
import com.example.assignment.helper.FileUploadHelper;
import com.example.assignment.model.User;
import com.example.assignment.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component("userService")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileUploadHelper fileUploadHelper;

    public List<User> getAllUsers(){

        return userRepository.findAll();
    }

    public User createUser(@RequestBody User user)
    {
//        ,@RequestParam("image") MultipartFile multipartFile
//        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//        user.setPhotos(fileName);
//        String uploadDir = "user-photos/" + user.getId();
//        fileUploadHelper.uploadFile(uploadDir, fileName, multipartFile);
        return userRepository.save(user);
    }

    public User getUserById(@PathVariable Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new MyNotFoundException("User not exist with id : "+id));
        return user;
    }

    public User updateUser(@PathVariable Long id, @RequestBody User userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not exist with id : "+id));
        user.setName(userDetails.getName());
        user.setEmailId(userDetails.getEmailId());
        user.setMobilenum(userDetails.getMobilenum());
        user.setGender(userDetails.getGender());
        user.setState(userDetails.getState());
        user.setSkills(userDetails.getSkills());
        userRepository.save(user);
        return user;
    }

    //    @PostMapping("/users")
//    public ResponseEntity<String> createUser(@RequestBody User user,@RequestParam("file") MultipartFile file)
//    {
//
//        try{
//            if(file.isEmpty()){
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No file found");
//            }
//            boolean f = fileUploadHelper.uploadFile(file);
//            userRepository.save(user);
//            if(f){
//                return ResponseEntity.ok("File Uploaded");
//            }
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//
//    }
}
