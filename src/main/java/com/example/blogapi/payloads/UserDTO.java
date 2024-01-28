package com.example.blogapi.payloads;

import com.example.blogapi.entities.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long Id;
    @NotEmpty
    @Size(min=3,message = "username should be minimum of 3 characters")
    private String name;
    @Email(message = "email should be valid")
    private String email;
    @NotEmpty
    @Size(min=3,max=10,message="password should be min 3 and max 10 characters")
    private String password;
    @NotEmpty
    private String about;
    private List<Role> roles;
}
