package com.example.booksearch.service;

import com.example.booksearch.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    private void 유저생성() {
        boolean success = userService.create("ddd","dean","222");
        Assert.assertTrue(success);
    }
}
