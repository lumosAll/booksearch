package com.example.booksearch.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private String password;

    protected User() {}

    public User(String userId, String name, String password) {
        this.userId=userId;
        this.name=name;
        this.password=password;
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
}
