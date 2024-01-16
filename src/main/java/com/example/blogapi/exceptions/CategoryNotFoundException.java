package com.example.blogapi.exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(long id){
        super("Category with id: "+id+" not found");
    }
}
