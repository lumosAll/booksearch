package com.example.booksearch.service.impl;

import com.example.booksearch.config.properties.KakaoApiProperties;
import com.example.booksearch.dto.KakaoBookSearchResponse;
import com.example.booksearch.service.ApiService;
import com.example.booksearch.service.KakaoApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Kakao Api Service
 */
@Service
@Slf4j
public class KakaoApiServiceImpl extends ApiService implements KakaoApiService {

    @Autowired
    KakaoApiProperties kakaoApiProperties;

    @Override
    public KakaoBookSearchResponse searchBook(String query, int page, int size) {

        Map<String,Object> params = new HashMap<>();
        params.put("query",query);
        params.put("page",page);
        params.put("size",size);

        KakaoBookSearchResponse kakaoBookSearchResponse = get(kakaoApiProperties.getDomain(),kakaoApiProperties.getBookSearchApi(),params,KakaoBookSearchResponse.class);
        return kakaoBookSearchResponse;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","KakaoAK "+kakaoApiProperties.getApiKey());
        return headers;
    }

}
