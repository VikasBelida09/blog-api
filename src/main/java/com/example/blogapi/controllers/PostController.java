package com.example.blogapi.controllers;

import com.example.blogapi.AppConstants;
import com.example.blogapi.entities.Post;
import com.example.blogapi.entities.PostResponse;
import com.example.blogapi.payloads.PostDTO;
import com.example.blogapi.services.FileService;
import com.example.blogapi.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
     private String path;
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
    @PostMapping("/{postId}/image/upload")
    public ResponseEntity<PostDTO> uploadImage(@PathVariable Long postId, @RequestParam("image")MultipartFile image) throws IOException {
        String imageName= this.fileService.uploadImage(this.path,image);
        PostDTO postDTO=this.postService.findPostById(postId);
        postDTO.setImageName(imageName);
        PostDTO updatedPost=this.postService.updatePost(postDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
    }
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE )
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }
}
