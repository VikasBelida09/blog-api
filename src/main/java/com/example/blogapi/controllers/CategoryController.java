package com.example.blogapi.controllers;

import com.example.blogapi.payloads.CategoryDTO;
import com.example.blogapi.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.getAllCategories());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id){
        return ResponseEntity.ok(this.categoryService.findById(id));
    }
    @PostMapping("/save")
    public ResponseEntity<CategoryDTO> saveCategory(@Valid @RequestBody CategoryDTO CategoryDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoryService.createCategory(CategoryDTO));
    }
    @PutMapping("/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO CategoryDTO){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.updateCategory(CategoryDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> updateCategory(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.categoryService.deleteCategoryById(id));
    }
}
