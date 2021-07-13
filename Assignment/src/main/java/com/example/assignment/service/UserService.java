package com.example.assignment.service;

import com.example.assignment.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface UserService {

     List<UserDto> getAllUsers();
     List<UserDto> UserPageable(Pageable pageable);
     UserDto getUserById(Long id);
     UserDto updateUser(Long id,UserDto userDetails);
     UserDto createUser(UserDto userDto, MultipartFile multipartFile);


}
