package com.example.booksearch.dto;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NaverBookSearchResponseAdapter implements BookSearchResponse {

    private List<BookDto> books;
    private int total;
    private int page;
    private int searchSize;

    public NaverBookSearchResponseAdapter(NaverBookSearchResponse naverBookSearchResponse,int page,int searchSize) {
        this.page = page;
        this.searchSize = searchSize;
        if(naverBookSearchResponse!=null&&naverBookSearchResponse.getItems()!=null) {
            this.total = naverBookSearchResponse.getTotal();
            this.books = naverBookSearchResponse.getItems().stream()
                    .map(item -> {
                        BookDto bookDto = new BookDto();
                        bookDto.setAuthors(Arrays.asList(item.getAuthor()));
                        bookDto.setContents(item.getDescription());
                        bookDto.setDatetime(item.getPubdate());
                        bookDto.setIsbn(item.getIsbn());
                        bookDto.setPrice(item.getPrice());
                        bookDto.setPublisher(item.getPublisher());
                        bookDto.setStatus(StringUtils.isNotEmpty(item.getPrice()) ? "정상판매" : "");
                        bookDto.setSalePrice(item.getDiscount());
                        bookDto.setThumbnail(item.getImage());
                        bookDto.setTitle(item.getTitle());
                        bookDto.setTranslators(Arrays.asList(""));
                        bookDto.setUrl(item.getLink());
                        return bookDto;
                    })
                    .collect(Collectors.toList());
        } else {
            this.total = 0;
            this.books = new ArrayList<>();
        }
    }
}
