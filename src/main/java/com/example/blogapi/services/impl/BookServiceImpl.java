package com.example.blogapi.services.impl;

import com.example.blogapi.entities.Book;
import com.example.blogapi.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl {
    @Autowired
    private BookRepository bookRepository;
    public List<Book> getAll(){
        return this.bookRepository.findAll();
    }
    public Book save(Book book){
        return this.bookRepository.save(book);
    }
    public Book findById(Long id){
        return this.bookRepository.findById(id).get();
    }
}
