package com.example.blogapi.controllers;

import com.example.blogapi.AppConstants;
import com.example.blogapi.entities.PostResponse;
import com.example.blogapi.payloads.PostDTO;
import com.example.blogapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/save")
    public ResponseEntity<PostDTO> createPost(@PathVariable Long userId, @PathVariable Long categoryId, @RequestBody PostDTO postDTO) {
        PostDTO post = this.postService.createPost(postDTO, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDTO>> getAllPostsOfaUser(@PathVariable Long userId) {
        return ResponseEntity.ok(this.postService.findPostsByUser(userId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDTO>> getAllPostsByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.postService.findPostByCategory(categoryId));
    }

    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize, @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber, @RequestParam(defaultValue = AppConstants.SORT_BY, required = false) String sortBy, @RequestParam(defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
        return ResponseEntity.ok(this.postService.getAllPosts(pageSize, pageNumber, sortBy, sortDir));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getAllPosts(@PathVariable Long postId) {
        return ResponseEntity.ok(this.postService.findPostById(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePostById(@PathVariable Long postId) {
        return ResponseEntity.ok(this.postService.deletePostById(postId));
    }

    @PutMapping("/update")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(this.postService.updatePost(postDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> search(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(this.postService.searchByPostTitle(keyword));
    }
}
