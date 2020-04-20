package com.example.booksearch.service;

import com.example.booksearch.dto.KakaoBookSearchResponse;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoApiServiceTest {

    @Autowired
    KakaoApiService kakaoApiService;

    @Test
    public void 카카오책검색() {
        KakaoBookSearchResponse kakaoBookSearchResponse = kakaoApiService.searchBook("해리포터",1,10);

        Assert.assertTrue(kakaoBookSearchResponse!=null);

        //없는책이름검색하기abcdefgzngh
        KakaoBookSearchResponse kakaoBookSearchResponse1 = kakaoApiService.searchBook("없는책이름검색하기abcdefgzngh",1,10);

        Assert.assertTrue(kakaoBookSearchResponse1!=null);

    }
}
