package com.example.blogapi.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtRequest {
    String name;
    String password;
}
