package com.example.booksearch.controller;

import com.example.booksearch.constants.JwtInfo;
import com.example.booksearch.constants.ResponseCode;
import com.example.booksearch.dto.ApiResponse;
import com.example.booksearch.dto.ErrorResponse;
import com.example.booksearch.exceptions.UserException;
import com.example.booksearch.service.UserService;
import com.example.booksearch.service.UserTokenService;
import com.example.booksearch.util.DateUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserTokenService userTokenService;

    /**
     * Sign In
     * @param id
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/signin")
    public ApiResponse login(
            @RequestParam(required = true) String id,
            @RequestParam(required = true) String password,
            HttpServletRequest request, HttpServletResponse response
    ) {

        userService.login(id,password);
        String token = userTokenService.create(id, DateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT));
        return ApiResponse.of(token);

    }

    /**
     * Sign Up
     * @param id
     * @param password
     * @param name
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/signup")
    public ApiResponse signUp(
            @RequestParam(required = true) String id,
            @RequestParam(required = true) String password,
            @RequestParam(required = false, defaultValue = "noname") String name,
            HttpServletRequest request, HttpServletResponse response
    ) {

        boolean result = userService.create(id,name,password);
        return ApiResponse.of(result);

    }

    /**
     * Refresh Token
     * @param authorization
     * @param request
     * @param response
     * @return
     */
    @PostMapping(value = "/refresh")
    public ApiResponse refresh(
            @RequestHeader(value = JwtInfo.HEADER_NAME) String authorization,
            HttpServletRequest request, HttpServletResponse response
    ) {
        String token = authorization.replaceAll("Bearer ","");;

        userTokenService.verify(token);
        String id = userTokenService.getUserId(token);
        String newToken = userTokenService.create(id, DateUtil.nowAfterDaysToDate(JwtInfo.EXPIRES_LIMIT));

        return ApiResponse.of(newToken);
    }

    @ExceptionHandler(UserException.class)
    public @ResponseBody
    ErrorResponse userExceptionHandler(UserException e) {
        log.error("UserController userException handler : {} ",e);
        return ErrorResponse.of(e.getMessage(), ResponseCode.FAIL);
    }

    /**
     * Exception Handler
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ErrorResponse exceptionHandler(Exception e) {
        log.error("UserController exception handler : {} ",e);
        return ErrorResponse.of("error", ResponseCode.ERROR);
    }

}
