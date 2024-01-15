package com.example.blogapi.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long Id;
    private String name;
    private String email;
    private String password;
    private String about;
}
