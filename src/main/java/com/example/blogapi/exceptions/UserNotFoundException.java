package com.example.blogapi.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
       super("user not found with id:"+id);
    }
}
