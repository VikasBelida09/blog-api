package com.example.blogapi.jwt;

import com.example.blogapi.payloads.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private UserDTO userDTO;
}
