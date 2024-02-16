package com.example.blogapi.controllers;

import com.example.blogapi.entities.Book;
import com.example.blogapi.inputs.BookRequest;
import com.example.blogapi.services.impl.BookServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
  @Autowired
    private BookServiceImpl bookService;
  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/all")
    private ResponseEntity<List<Book>> getAll(){
      return ResponseEntity.ok(this.bookService.getAll());
  }
  @QueryMapping
  private List<Book> getAllBooks(){
    return this.bookService.getAll();
  }
  @QueryMapping
  private Book getById(@Argument long bookId){
    return this.bookService.findById(bookId);
  }

  @PostMapping("/save")
    private ResponseEntity<Book> save(@RequestBody Book book){
      return ResponseEntity.status(HttpStatus.CREATED).body(this.bookService.save(book));
  }
  @MutationMapping
  private Book saveBook(@Argument Book book){
    return this.bookService.save(book);
  }

}
