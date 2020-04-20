package com.example.booksearch.service;

import com.example.booksearch.dto.NaverBookSearchResponse;
import org.springframework.stereotype.Service;

@Service
public interface NaverApiService {

    NaverBookSearchResponse searchBook(String query, int page, int size);
}
