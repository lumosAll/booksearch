package com.example.booksearch.repository;

import com.example.booksearch.entity.BookSearchStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookSearchStatRepository extends JpaRepository<BookSearchStat,String> {

    @Transactional(propagation = Propagation.REQUIRED)
    @Modifying
    @Query(value="update BookSearchStat set search_count = search_count + :addSearchCount, lastDate = now() WHERE keyword = :keyword", nativeQuery=false)
    Integer updateSearchCount(@Param("keyword") String keyword,@Param("addSearchCount")  Integer addSearchCount);

}
