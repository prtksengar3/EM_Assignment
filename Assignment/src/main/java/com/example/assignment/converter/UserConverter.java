package com.example.assignment.converter;

import com.example.assignment.dto.UserDto;
import com.example.assignment.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserConverter {

    public UserDto entityToDto(User user){
        System.out.println(user.toString());
     UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmailId(user.getEmailId());
        userDto.setMobilenum(user.getMobilenum());
        userDto.setGender(user.getGender());
        userDto.setState(user.getState());
        userDto.setSkills(user.getSkills());
        userDto.setPhotos(user.getPhotos());
        return  userDto;
    }

    public List<UserDto> entityToDto(List<User> user){

        List<UserDto> userDto = user.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
        return  userDto;
    }

    public User dtoToEntity (UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmailId(userDto.getEmailId());
        user.setMobilenum(userDto.getMobilenum());
        user.setGender(userDto.getGender());
        user.setState(userDto.getState());
        user.setSkills(userDto.getSkills());
        user.setPhotos(userDto.getPhotos());
        return  user;
    }

    public List<User> dtoToEntity (List<UserDto> userDto){
        List<User> user = userDto.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
        return  user;
    }
}
