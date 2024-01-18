package com.example.blogapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Date createdDate;
    private String imageName;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist(){
        this.createdDate=new Date();
    }

}
