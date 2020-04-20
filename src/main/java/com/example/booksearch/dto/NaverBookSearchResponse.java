package com.example.booksearch.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NaverBookSearchResponse {

    private int total;
    private int start;
    private int display;

    private List<Item> items;

    @Data
    public class Item{
        private String title;
        private String link;
        private String image;
        private String author;
        private String price;
        private String discount;
        private String publisher;
        private String isbn;
        private String description;
        private String pubdate;
    }
}
