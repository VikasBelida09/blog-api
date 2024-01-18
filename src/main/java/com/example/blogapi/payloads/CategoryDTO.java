package com.example.blogapi.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    @NotEmpty(message = "category title can not be empty")
    @Size(min=3,max=20,message = "category title should be min 3 characters and max 20 characters")
    private String categoryTitle;
    @NotEmpty(message = "category description can not be empty")
    private String categoryDescription;
}
