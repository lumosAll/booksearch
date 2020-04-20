package com.example.booksearch.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name="USER_TOKEN",
        indexes = {
                @Index(name="USER_TOKEN_IDX_1", columnList = "USER_ID")
        }
)
public class UserToken {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long seq;

    @Column(name="USER_ID", nullable = false)
    private String userId;
    @Column(name="TOKEN", length = 1000, nullable = false)
    private String token;


    protected UserToken() {}

    public UserToken(String userId, String token) {

        this.userId = userId;
        this.token = token;
    }

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
        return "("+userId+","+token+","+regDate+")";
    }
}
