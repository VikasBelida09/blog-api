package com.example.blogapi.services;

import com.example.blogapi.entities.Category;
import com.example.blogapi.entities.Post;
import com.example.blogapi.entities.PostResponse;
import com.example.blogapi.entities.User;
import com.example.blogapi.payloads.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO post,Long userId,Long categoryId);
    PostDTO updatePost(PostDTO post);
    PostDTO findPostById(Long id);
    boolean deletePostById(Long id);
    PostResponse getAllPosts(Integer pageSize, Integer pageNumber,String sortBy, String sortDit);
    List<PostDTO> searchByPostTitle(String title);

    List<PostDTO> findPostsByUser(Long userId);

    List<PostDTO> findPostByCategory(Long categoryId);


}
