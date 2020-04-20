package com.example.booksearch.service;

import com.example.booksearch.dto.BookSearchResponse;

public interface BookSearchService {

    BookSearchResponse searchBook(String query, int page, int size);
}
