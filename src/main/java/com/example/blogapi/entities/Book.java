package com.example.blogapi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="book")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private Double price;
    private Integer pages;

    private String type;

    private Date publishedDate;
    private Double rating;

    private String about;

    private String summary;

    private boolean isBookAvailableOnline;
    private boolean isInStock;

}
