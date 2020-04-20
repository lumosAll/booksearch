package com.example.booksearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class BooksearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksearchApplication.class, args);
    }

}
