package com.example.booksearch.service;

import com.example.booksearch.dto.NaverBookSearchResponse;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NaverApiServiceTest {

    @Autowired
    NaverApiService naverApiService;

    @Test
    public void 네이버책검색() {
        NaverBookSearchResponse naverBookSearchResponse = naverApiService.searchBook("해리포터",1,10);

        Assert.assertTrue(naverBookSearchResponse!=null);

        NaverBookSearchResponse naverBookSearchResponse1 = naverApiService.searchBook("없는책이름검색하기abcdefgzngh",1,10);
        Assert.assertTrue(naverBookSearchResponse1!=null);

    }
}
