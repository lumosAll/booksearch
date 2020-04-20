package com.example.booksearch.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class KakaoBookSearchResponse {

    private Meta meta;
    private List<Document> documents;

    @Data
    public class Meta {
        private int total_count;
        private int pageable_count;
        private boolean is_end;
    }

    @Data
    public class Document {
        private List<String> authors;
        private String contents;
        private String datetime;
        private String isbn;
        private String price;
        private String publisher;
        private String sale_price;
        private String status;
        private String thumbnail;
        private String title;
        private List<String> translators;
        private String url;
    }
}
