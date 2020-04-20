package com.example.booksearch.service;

import com.example.booksearch.entity.BookSearchStat;
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
@Transactional
@Rollback
public class BookSearchStatServiceTest {

    @Autowired
    BookSearchStatService bookSearchStatService;

    @Test
    public void 최대검색어10() {

        bookSearchStatService.addBookSearchStat("test");
        List<BookSearchStat> bookSearchStatList = bookSearchStatService.getBookSearchStatTop10();

        Assert.assertTrue(bookSearchStatList.size()>0);

    }
}
