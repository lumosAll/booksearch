package com.example.booksearch.service.impl;

import com.example.booksearch.config.properties.NaverApiProperties;
import com.example.booksearch.dto.NaverBookSearchResponse;
import com.example.booksearch.service.ApiService;
import com.example.booksearch.service.NaverApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Naver Api Service
 */
@Slf4j
@Service
public class NaverApiServiceImpl extends ApiService implements NaverApiService {

    @Autowired
    NaverApiProperties naverApiProperties;

    @Override
    public NaverBookSearchResponse searchBook(String query, int page, int size) {

        int start = (page-1)*size+1;

        Map<String,Object> params = new HashMap<>();
        params.put("query",query);
        params.put("start",start);
        params.put("display",size);

        NaverBookSearchResponse naverBookSearchResponse = get(naverApiProperties.getDomain(),naverApiProperties.getBookSearchApi(),params,NaverBookSearchResponse.class);
        return naverBookSearchResponse;
    }

    protected HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",naverApiProperties.getClientId());
        headers.set("X-Naver-Client-Secret",naverApiProperties.getClientSecret());
        return headers;
    }

}
