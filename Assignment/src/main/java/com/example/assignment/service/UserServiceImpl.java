package com.example.assignment.service;

import com.example.assignment.converter.UserConverter;
import com.example.assignment.dto.UserDto;
import com.example.assignment.exception.MyNotFoundException;
import com.example.assignment.exception.ResourceNotFoundException;
import com.example.assignment.helper.FileUploadHelper;
import com.example.assignment.model.User;
import com.example.assignment.repository.UserRepository;
import org.hibernate.Transaction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@Component("userService")
@Service
public class UserServiceImpl implements UserService {
    @Autowired private UserRepository userRepository;
    @Autowired private FileUploadHelper fileUploadHelper;
    @Autowired private UserConverter userConverter;
    @Autowired private ModelMapper modelMapper;

    public List<UserDto> getAllUsers(){
        List<User> findAll = userRepository.findAll();
        return userConverter.entityToDto(findAll);
    }

    public List<UserDto> UserPageable(Pageable pageable){
        Page<User> userPage = userRepository
                .findAll(pageable);
        Page<UserDto> videos = userPage.map(obj -> modelMapper.map(obj, UserDto.class));
        List<UserDto> videosList = videos.getContent();
        return  videosList;
    }

    public UserDto createUser(UserDto userDto,MultipartFile multipartFile)
    {
        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()){
        try{
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            userDto.setPhotos(fileName);
            String uploadDir = "user-photos/";
            fileUploadHelper.uploadFile(uploadDir, fileName, multipartFile);
            User user = userConverter.dtoToEntity(userDto);
            userRepository.save(user);
//            transaction.commit();
            return userConverter.entityToDto(user);
        }
        catch (Exception e){
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }

        return null;
    }


    public UserDto getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new MyNotFoundException("User not exist with id : "+id));
        return userConverter.entityToDto(user);
    }

    public UserDto updateUser(Long id,UserDto userDetails){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not exist with id : "+id));
        user.setName(userDetails.getName());
        user.setEmailId(userDetails.getEmailId());
        user.setMobilenum(userDetails.getMobilenum());
        user.setGender(userDetails.getGender());
        user.setState(userDetails.getState());
        user.setSkills(userDetails.getSkills());
        userRepository.save(user);
        return userConverter.entityToDto(user);
    }

}
