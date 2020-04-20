package com.example.booksearch.dto;

import com.example.booksearch.constants.ResponseCode;
import lombok.Data;

import java.util.stream.Stream;

@Data
public class ApiResponse {

    private ApiResponse() {}

    private ResponseCode code = ResponseCode.SUCCESS;

    private Object data;

    public static ApiResponse of(Object data) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(data);

        return apiResponse;
    }
}
