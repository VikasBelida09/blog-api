package com.example.blogapi.services;

import com.example.blogapi.entities.Category;
import com.example.blogapi.entities.Post;
import com.example.blogapi.entities.User;
import com.example.blogapi.payloads.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO post,Long userId,Long categoryId);
    Post updatePost(PostDTO post);
    Post finePostById(Long id);
    boolean deletePostById(Long id);
    List<PostDTO> getAllPosts();
    List<Post> searchByPostTitle(String title);

    List<Post> findPostsByUser(User user);

    List<Post> findPostByCategory(Category category);


}
