package com.example.blogapi.exceptions;

public class RoleNotFoundException extends RuntimeException{
    RoleNotFoundException(Long id){
        super("Role not found with id: "+id);
    }

}

