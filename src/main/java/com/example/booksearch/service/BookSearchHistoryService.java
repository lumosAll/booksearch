package com.example.booksearch.service;

import com.example.booksearch.entity.BookSearchHistory;
import com.example.booksearch.entity.User;

import java.util.List;

public interface BookSearchHistoryService {

    void addBookSearchHistory(User user, String query);
    List<BookSearchHistory> getHistory(String userId, int page, int size);
}
