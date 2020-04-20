package com.example.booksearch.repository;

import com.example.booksearch.entity.BookSearchHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookSearchHistoryRepository extends JpaRepository<BookSearchHistory,Long> {

    List<BookSearchHistory> findListByUserId(String id,Pageable pageable);
}
