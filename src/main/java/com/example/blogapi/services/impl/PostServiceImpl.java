package com.example.blogapi.services.impl;

import com.example.blogapi.entities.Category;
import com.example.blogapi.entities.Post;
import com.example.blogapi.entities.User;
import com.example.blogapi.exceptions.CategoryNotFoundException;
import com.example.blogapi.exceptions.UserNotFoundException;
import com.example.blogapi.payloads.PostDTO;
import com.example.blogapi.repositories.CategoryRepository;
import com.example.blogapi.repositories.PostRepository;
import com.example.blogapi.repositories.UserRepository;
import com.example.blogapi.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDto,Long userId,Long categoryId) {
        User user=this.userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
        Post post=this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setUser(user);
        post.setCategory(category);
        return this.modelMapper.map(this.postRepository.save(post),PostDTO.class);
    }

    @Override
    public Post updatePost(PostDTO post) {
        return null;
    }

    @Override
    public Post finePostById(Long id) {
        return null;
    }

    @Override
    public boolean deletePostById(Long id) {
        return false;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return null;
    }

    @Override
    public List<Post> searchByPostTitle(String title) {
        return null;
    }

    @Override
    public List<Post> findPostsByUser(User user) {
        return null;
    }

    @Override
    public List<Post> findPostByCategory(Category category) {
        return null;
    }
}
