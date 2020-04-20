package com.example.booksearch.repository;

import com.example.booksearch.entity.BookSearchHistory;
import com.example.booksearch.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "transactionManager")
@Rollback
public class BookSearchHistoryRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookSearchHistoryRepository bookSearchHistoryRepository;

    @Test
    public void 히스토리생성() {
        User user = userRepository.findAll().get(0);

        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"해리포터"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"나니아연대기"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"총균쇠"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"바른생활"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"반지의제왕"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"시지프스신화"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"율리우스"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"심청전"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"구운몽"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"붉은포도밭"));
        bookSearchHistoryRepository.save(new BookSearchHistory(user.getUserId(),"질문의책"));

        List<BookSearchHistory> list = bookSearchHistoryRepository.findListByUserId(user.getUserId(),PageRequest.of(0,5, Sort.Direction.DESC, "regDate"));

        assertTrue(list.size()==5);
        assertTrue(list.get(0).getSearchText().equals("질문의책"));

    }
}
