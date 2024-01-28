package com.example.blogapi.controllers;

import com.example.blogapi.jwt.JwtHelper;
import com.example.blogapi.jwt.JwtRequest;
import com.example.blogapi.jwt.JwtResponse;
import com.example.blogapi.payloads.UserDTO;
import com.example.blogapi.services.UserService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.LogManager;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.createUser(userDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest){
        logger.info("performing authentication");
        Authentication authentication=new UsernamePasswordAuthenticationToken(jwtRequest.getName(),jwtRequest.getPassword());
        Authentication auth=authenticationManager.authenticate(authentication);
        if (auth.isAuthenticated()) {
            logger.info("authentication success");
            JwtResponse jwtResponse=new JwtResponse(jwtHelper.generateToken(jwtRequest.getName()),this.modelMapper.map(userService.findByUserName(jwtRequest.getName()),UserDTO.class));
            return ResponseEntity.ok(jwtResponse);
        } else {
            logger.error("authentication failed");
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(userDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(id));
    }

}
