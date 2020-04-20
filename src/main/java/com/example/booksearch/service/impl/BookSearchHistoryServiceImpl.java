package com.example.booksearch.service.impl;

import com.example.booksearch.entity.BookSearchHistory;
import com.example.booksearch.entity.User;
import com.example.booksearch.repository.BookSearchHistoryRepository;
import com.example.booksearch.service.BookSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 책 검색 이력
 */
@Service
public class BookSearchHistoryServiceImpl implements BookSearchHistoryService {

    @Autowired
    BookSearchHistoryRepository bookSearchHistoryRepository;

    @Override
    @Async
    public void addBookSearchHistory(User user, String query) {
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),query));
    }

    @Override
    public List<BookSearchHistory> getHistory(String userId, int page, int size) {
        return bookSearchHistoryRepository.findListByUserId(userId, PageRequest.of((page-1<0)?0:(page-1),size, Sort.Direction.DESC, "regDate"));
    }
}
