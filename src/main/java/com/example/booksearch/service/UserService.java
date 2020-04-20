package com.example.booksearch.service;

import com.example.booksearch.entity.User;

public interface UserService {

    boolean create(String id, String name, String password);
    User get(String id);
    boolean delete(String id);
    boolean login(String id, String password);
}
