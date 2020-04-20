package com.example.booksearch.service;

import com.example.booksearch.entity.BookSearchHistory;
import com.example.booksearch.entity.User;
import com.example.booksearch.repository.UserRepository;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class BookSearchHistoryServiceTest {

    @Autowired
    BookSearchHistoryService bookSearchHistoryService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void 내검색이력() {
        List<User> userList = userRepository.findAll(PageRequest.of(1,1)).getContent();
        if(userList.size()>0) {
            User user = userList.get(0);
            List<BookSearchHistory> bookSearchHistoryList = bookSearchHistoryService.getHistory(user.getUserId(),1,10);

            Assert.assertTrue(bookSearchHistoryList!=null);

        }
        List<BookSearchHistory> bookSearchHistoryList = bookSearchHistoryService.getHistory("-1",1,10);

        Assert.assertTrue(bookSearchHistoryList!=null);

    }
}
