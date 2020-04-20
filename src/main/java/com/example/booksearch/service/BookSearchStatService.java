package com.example.booksearch.service;

import com.example.booksearch.entity.BookSearchStat;

import java.util.List;

public interface BookSearchStatService {

    void addBookSearchStat(String query);
    List<BookSearchStat> getBookSearchStatTop10();
}
