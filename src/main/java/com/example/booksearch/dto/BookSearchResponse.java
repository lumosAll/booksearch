package com.example.booksearch.dto;

import java.util.List;

public interface BookSearchResponse {
    int getPage();
    int getTotal();
    int getSearchSize();
    List<BookDto> getBooks();

}
