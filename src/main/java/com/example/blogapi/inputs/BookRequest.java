package com.example.blogapi.inputs;

public record BookRequest(
        String name,
        String author,
        Double price,
        Integer pages,
        String type,
        String publishedDate,
        Integer rating,
        String about,
        String summary,
                Boolean isBookAvailableOnline,
        Boolean isInStock
) {
}
