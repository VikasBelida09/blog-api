package com.example.blogapi.controllers;

import com.example.blogapi.entities.Post;
import com.example.blogapi.payloads.PostDTO;
import com.example.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/save")
    public ResponseEntity<PostDTO> createPost(@PathVariable Long userId, @PathVariable Long categoryId, @RequestBody PostDTO postDTO){
        PostDTO post = this.postService.createPost(postDTO,userId,categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }
}
