package com.example.booksearch.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOOK_SEARCH_HISTORY",
        indexes = {
                @Index(name = "BOOK_SEARCH_HISTORY_IDX1", unique = false, columnList = "USER_ID, REG_DATE")
        })
public class BookSearchHistory {

    protected BookSearchHistory(){}

    public BookSearchHistory(String userId,String searchText) {
        this.userId = userId;
        this.searchText = searchText;
    }

    @Id
    @GeneratedValue
    @Column(name = "BOOK_SEARCH_ID")
    private Long id;

    @Column(name="USER_ID")
    private String userId;

    private String searchText;

    @Column(name = "REG_DATE",updatable = false, nullable = false)
    private LocalDateTime regDate;
    @PrePersist
    protected void onCreate() {
        regDate = LocalDateTime.now();
        lastDate = regDate;
    }

    private LocalDateTime lastDate;
    @PreUpdate
    protected void onUpdate() {
        lastDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "("+id+","+userId+","+searchText+","+regDate+")";
    }
}
