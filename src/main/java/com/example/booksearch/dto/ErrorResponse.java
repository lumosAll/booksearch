package com.example.booksearch.dto;

import com.example.booksearch.constants.ResponseCode;
import lombok.Data;

@Data
public class ErrorResponse {

    private ErrorResponse() {}

    private String message;
    private ResponseCode code;


    public static ErrorResponse of(String message,ResponseCode code) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(code);
        response.setMessage(message);

        return response;
    }
}
