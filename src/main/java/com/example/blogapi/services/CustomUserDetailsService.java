package com.example.blogapi.services;

import com.example.blogapi.entities.User;
import com.example.blogapi.services.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CustomUserDetailsService(UserService userService, ModelMapper modelMapper){
        this.userService=userService;
        this.modelMapper=modelMapper;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userService.findByUserName(username);
        if(Objects.isNull(user))throw new UsernameNotFoundException(username);
        return this.modelMapper.map(user, UserDetails.class);
    }
}

