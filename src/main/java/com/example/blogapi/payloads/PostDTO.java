package com.example.blogapi.payloads;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String createdDate;
    private Long id;
    private String title;
    private String content;
    private String imageName;
    private UserDTO user;
    private CategoryDTO category;
}
