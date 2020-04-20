package com.example.booksearch.service;

import java.util.Date;

public interface UserTokenService {

    String create(String userId, Date date) ;
    Boolean verify(String token);
    String getUserId(String token);
}
