package com.example.blogapi.services.impl;

import com.example.blogapi.entities.User;
import com.example.blogapi.exceptions.UserNotFoundException;
import com.example.blogapi.payloads.UserDTO;
import com.example.blogapi.repositories.UserRepository;
import com.example.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user=this.userRepository.save(this.dtoToUser(userDTO));
        return this.userToDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return this.userRepository.findAll().stream().map(this::userToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return this.userToDTO(user);
    }

    @Override
    public UserDTO updateUser(UserDTO user) {
        User user1=this.userRepository.findById(user.getId()).orElseThrow(()->new UserNotFoundException(user.getId()));
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setAbout(user.getAbout());

        this.userRepository.save(user1);
        return this.userToDTO(user1);
    }

    @Override
    public boolean deleteUser(Long id) {
        User user=this.userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
        this.userRepository.deleteById(user.getId());
        return true;
    }
    private User dtoToUser(UserDTO userDTO){
        User user=new User();
        user.setAbout(userDTO.getAbout());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    private UserDTO userToDTO(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setAbout(user.getAbout());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
