package com.example.booksearch.service;

import com.example.booksearch.entity.UserToken;
import com.example.booksearch.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
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
public class UserTokenServiceTest {

    @Autowired
    UserTokenService userTokenService;

    @Test
    public void 유저토큰생성() {

        String token = userTokenService.create("aaa", DateUtil.nowAfterDaysToDate(3l));
        Assert.assertTrue(StringUtils.isNotBlank(token));
    }
}
