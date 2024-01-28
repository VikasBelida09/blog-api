package com.example.blogapi.services;

import com.example.blogapi.entities.User;
import com.example.blogapi.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    User findByUserName(String userName);
    UserDTO updateUser(UserDTO user);

    boolean deleteUser(Long id);
}
