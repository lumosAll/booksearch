package com.example.booksearch.service;

import com.example.booksearch.dto.BookDto;
import com.example.booksearch.dto.BookSearchResponse;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookSearchServiceTest {

    @Autowired
    BookSearchService bookSearchService;

    @Test
    public void 책검색() {
        BookSearchResponse bookSearchResponse = bookSearchService.searchBook("해리포터",1,10);

        Assert.assertTrue(bookSearchResponse!=null);

        //없는책이름검색하기abcdefgzngh
        BookSearchResponse bookSearchResponse1 = bookSearchService.searchBook("없는책이름검색하기abcdefgzngh",1,10);

        Assert.assertTrue(bookSearchResponse1!=null);

    }
}
