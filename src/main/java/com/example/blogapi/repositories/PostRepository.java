package com.example.blogapi.repositories;

import com.example.blogapi.entities.Category;
import com.example.blogapi.entities.Post;
import com.example.blogapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContainingIgnoreCase(String title);
}
