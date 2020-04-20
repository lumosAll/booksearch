package com.example.booksearch.repository;

import com.example.booksearch.entity.BookSearchStat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookSearchStatRepositoryTest {

    @Autowired
    BookSearchStatRepository bookSearchStatRepository;

    @Test
    @Transactional
    @Rollback
    public void 최대검색어10건() {

        bookSearchStatRepository.save(new BookSearchStat("해리포터",10298));
        bookSearchStatRepository.save(new BookSearchStat("나니아연대기",9986));
        bookSearchStatRepository.save(new BookSearchStat("반지의 제왕",20876));
        bookSearchStatRepository.save(new BookSearchStat("구토",46));
        bookSearchStatRepository.save(new BookSearchStat("전락",89));
        bookSearchStatRepository.save(new BookSearchStat("칼의 노래",6890));
        bookSearchStatRepository.save(new BookSearchStat("생각에 관한 생각",3987));

        List<BookSearchStat> list
                = bookSearchStatRepository.findAll(PageRequest.of(0,5, Sort.Direction.DESC,"searchCount")).getContent();

        assertTrue(list.size()==5);
        assertTrue(list.get(0).getKeyword().equals("반지의 제왕"));
    }

    @Test
    public void 검색숫자업데이트() {
        BookSearchStat bookSearchStat1 = new BookSearchStat("해리포터",10298);
        bookSearchStatRepository.save(bookSearchStat1);
        bookSearchStatRepository.updateSearchCount("해리포터", 1);
        BookSearchStat bookSearchStat = bookSearchStatRepository.findById("해리포터").get();

        System.out.println(bookSearchStat);
        assertTrue(bookSearchStat.getSearchCount()==10298+1);
    }

    @Test
    public void 특정키워드검색() {
        BookSearchStat bookSearchStat = bookSearchStatRepository.findById("해리포터").get();
        System.out.println(bookSearchStat);
    }
}
