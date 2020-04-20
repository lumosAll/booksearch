package com.example.booksearch.service;

import com.example.booksearch.dto.KakaoBookSearchResponse;

public interface KakaoApiService {

    KakaoBookSearchResponse searchBook(String query, int page, int size);
}
