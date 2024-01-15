package com.example.blogapi.services;

import com.example.blogapi.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO user);

    boolean deleteUser(Long id);
}
