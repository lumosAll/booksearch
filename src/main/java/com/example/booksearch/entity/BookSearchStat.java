package com.example.booksearch.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "BOOK_SEARCH_STAT",
        indexes = {
                @Index(name = "BOOK_SEARCH_STAT_IDX1", unique = false, columnList = "SEARCH_COUNT")
        })
public class BookSearchStat {

    protected BookSearchStat(){

    }
    public BookSearchStat(String keyword,int searchCount) {
        this.keyword = keyword;
        this.searchCount = searchCount;
    }

    @Id
    String keyword;

    @Column(name = "SEARCH_COUNT")
    Integer searchCount;

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
        return "keyword="+keyword
                +",searchCount="+searchCount;
    }

}
