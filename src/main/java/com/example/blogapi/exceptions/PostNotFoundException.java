package com.example.blogapi.exceptions;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Post not found with Id: "+id);
    }
}
