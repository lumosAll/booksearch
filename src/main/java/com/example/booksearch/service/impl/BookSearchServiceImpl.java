package com.example.booksearch.service.impl;

import com.example.booksearch.dto.BookSearchResponse;
import com.example.booksearch.dto.KakaoBookSearchResponseAdapter;
import com.example.booksearch.dto.NaverBookSearchResponseAdapter;
import com.example.booksearch.service.BookSearchService;
import com.example.booksearch.service.KakaoApiService;
import com.example.booksearch.service.NaverApiService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 책 검색 서비스
 */
@Slf4j
@Service
public class BookSearchServiceImpl implements BookSearchService {

    @Autowired
    KakaoApiService kakaoApiService;

    @Autowired
    NaverApiService naverApiService;

    @HystrixCommand(commandKey = "bookSearchService_searchBook", // 통계등 집계용 키
            fallbackMethod = "searchBookFallback",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000") }
    )
    public BookSearchResponse searchBook(String query,int page,int size) {

        return new KakaoBookSearchResponseAdapter(kakaoApiService.searchBook(query,page,size),page,size);
    }

    private BookSearchResponse searchBookFallback(String query,int page,int size) {

        return new NaverBookSearchResponseAdapter(naverApiService.searchBook(query,page,size),page,size);
    }

}
