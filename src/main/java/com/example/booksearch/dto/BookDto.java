package com.example.booksearch.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private List<String> authors;
    private String contents;
    private String datetime;
    private String isbn;
    private String price;
    private String publisher;
    private String salePrice;
    private String status;
    private String thumbnail;
    private String title;
    private List<String> translators;
    private String url;
}
