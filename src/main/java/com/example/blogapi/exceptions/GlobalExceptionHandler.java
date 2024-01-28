package com.example.blogapi.exceptions;

import com.example.blogapi.entities.Role;
import com.example.blogapi.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException ex){
      ApiResponse response=new ApiResponse();
      response.setMessage(ex.getMessage());
      response.setStatus(false);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse> handleAuthException(AuthenticationException ex){
        ApiResponse response=new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ApiResponse> handleRoleNotFoundException(RoleNotFoundException ex){
        ApiResponse response=new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ApiResponse> handlePostNotFoundException(PostNotFoundException ex){
        ApiResponse response=new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCategoryNotFoundException(CategoryNotFoundException ex){
        ApiResponse response=new ApiResponse();
        response.setMessage(ex.getMessage());
        response.setStatus(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String>map=new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((e)->{
            String field=((FieldError)e).getField();
            String message=e.getDefaultMessage();
            map.put(field,message);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}
