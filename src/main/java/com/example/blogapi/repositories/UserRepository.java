package com.example.blogapi.repositories;

import com.example.blogapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
