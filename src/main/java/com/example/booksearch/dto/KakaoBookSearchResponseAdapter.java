package com.example.booksearch.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class KakaoBookSearchResponseAdapter implements BookSearchResponse {

    private List<BookDto> books;
    private int total;
    private int page;
    private int searchSize;

    public KakaoBookSearchResponseAdapter(KakaoBookSearchResponse kakaoBookSearchResponse,int page,int searchSize) {
        this.page = page;
        this.searchSize = searchSize;
        if(kakaoBookSearchResponse!=null&&kakaoBookSearchResponse.getDocuments()!=null) {
            this.total = kakaoBookSearchResponse.getMeta().getPageable_count();
            this.books = kakaoBookSearchResponse.getDocuments().stream()
                    .map(document -> {
                        BookDto bookDto = new BookDto();
                        bookDto.setAuthors(document.getAuthors());
                        bookDto.setContents(document.getContents());
                        bookDto.setDatetime(document.getDatetime());
                        bookDto.setIsbn(document.getIsbn());
                        bookDto.setPrice(document.getPrice());
                        bookDto.setPublisher(document.getPublisher());
                        bookDto.setStatus(document.getStatus());
                        bookDto.setSalePrice(document.getSale_price());
                        bookDto.setThumbnail(document.getThumbnail());
                        bookDto.setTitle(document.getTitle());
                        bookDto.setTranslators(document.getTranslators());
                        bookDto.setUrl(document.getUrl());
                        return bookDto;
                    })
                    .collect(Collectors.toList());
        } else {
            this.total = 0;
            this.books = new ArrayList<>();
        }
    }

}
