package com.example.blogapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title",nullable = false)
    private String categoryTitle;
    @Column(name="description",nullable = false)
    private String categoryDescription;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();
}
