package com.example.blogapi.controllers;

import com.example.blogapi.payloads.UserDTO;
import com.example.blogapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(userDTO));
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(userDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(id));
    }

}
