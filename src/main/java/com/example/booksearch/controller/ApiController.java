package com.example.booksearch.controller;

import com.example.booksearch.constants.ResponseCode;
import com.example.booksearch.dto.ApiResponse;
import com.example.booksearch.dto.BookSearchResponse;
import com.example.booksearch.dto.ErrorResponse;
import com.example.booksearch.entity.BookSearchHistory;
import com.example.booksearch.entity.BookSearchStat;
import com.example.booksearch.entity.User;
import com.example.booksearch.exceptions.ApiException;
import com.example.booksearch.exceptions.UserException;
import com.example.booksearch.service.BookSearchHistoryService;
import com.example.booksearch.service.BookSearchService;
import com.example.booksearch.service.BookSearchStatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    BookSearchService bookSearchService;

    @Autowired
    BookSearchStatService bookSearchStatService;

    @Autowired
    BookSearchHistoryService bookSearchHistoryService;

    /**
     * 책 검색
     * @param query
     * @param page
     * @param size
     * @param user
     * @return
     */
    @GetMapping(value = "/search/book")
    public Mono<ApiResponse> getBookSearch(@RequestParam(required = true) String query,
                                                  @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestAttribute("user") User user) {

        BookSearchResponse bookSearchResponse = bookSearchService.searchBook(query,page,size);
        bookSearchStatService.addBookSearchStat(query);
        bookSearchHistoryService.addBookSearchHistory(user,query);
        return Mono.just(ApiResponse.of(bookSearchResponse));
    }

    /**
     * 최대 조회 검색어 10건
     * @return
     */
    @GetMapping(value = "/top10/query")
    public Mono<ApiResponse> getTop10Query() {
        List<BookSearchStat> bookSearchStatList = bookSearchStatService.getBookSearchStatTop10();

        return Mono.just(ApiResponse.of(bookSearchStatList));
    }

    /**
     * 내 검색 이력 조회
     * @param page
     * @param size
     * @param user
     * @return
     */
    @GetMapping(value = "/my/history")
    public Mono<ApiResponse> getBookSearchHistory(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestAttribute("user") User user) {
        List<BookSearchHistory> bookSearchHistoryList = bookSearchHistoryService.getHistory(user.getUserId(),page,size);

        return Mono.just(ApiResponse.of(bookSearchHistoryList));
    }

    @ExceptionHandler(UserException.class)
    public @ResponseBody
    ErrorResponse userExceptionHandler(UserException e) {
        log.error("ApiController userException handler : {} ",e);
        return ErrorResponse.of(e.getMessage(), ResponseCode.FAIL);
    }

    @ExceptionHandler(ApiException.class)
    public @ResponseBody ErrorResponse apiExceptionHandler(ApiException e) {
        log.error("ApiController apiException handler : {} ",e);
        return ErrorResponse.of(e.getMessage(), ResponseCode.FAIL);
    }

    /**
     * Exception Handler
     * TODO 고도화.
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody ErrorResponse exceptionHandler(Exception e) {
        log.error("ApiController exception handler : {} ",e);
        return ErrorResponse.of("error", ResponseCode.ERROR);
    }
}
