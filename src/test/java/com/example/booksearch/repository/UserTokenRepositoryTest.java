package com.example.booksearch.repository;

import com.example.booksearch.entity.UserToken;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(transactionManager = "transactionManager")
@Rollback
public class UserTokenRepositoryTest {

    @Autowired
    UserTokenRepository userTokenRepository;

    @Test
    public void 유저토큰생성(){
        userTokenRepository.save(new UserToken("1","token1"));
        userTokenRepository.save(new UserToken("1","token2"));
        userTokenRepository.save(new UserToken("1","token3"));

        List<UserToken> list = userTokenRepository.findAll(PageRequest.of(0,2, Sort.Direction.DESC,"regDate")).toList();

        System.out.println(list);
    }
}
