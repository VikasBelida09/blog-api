package com.example.blogapi.payloads;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private boolean status;
}
