package com.example.booksearch.service.impl;

import com.example.booksearch.entity.BookSearchStat;
import com.example.booksearch.repository.BookSearchStatRepository;
import com.example.booksearch.service.BookSearchStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 책 검색 통계
 */
@Service
public class BookSearchStatServiceImpl implements BookSearchStatService {

    @Autowired
    BookSearchStatRepository bookSearchStatRepository;

    @Override
    @Async
    @Transactional
    public void addBookSearchStat(String query) {
        if(bookSearchStatRepository.existsById(query))
            bookSearchStatRepository.updateSearchCount(query,1);
        else
            bookSearchStatRepository.save(new BookSearchStat(query,1));
    }

    @Override
    public List<BookSearchStat> getBookSearchStatTop10() {
        return bookSearchStatRepository.findAll(PageRequest.of(0,10, Sort.Direction.DESC,"searchCount")).getContent();
    }
}
