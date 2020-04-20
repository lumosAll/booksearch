package com.example.booksearch.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.booksearch.constants.JwtInfo;
import com.example.booksearch.entity.UserToken;
import com.example.booksearch.exceptions.UserException;
import com.example.booksearch.repository.UserTokenRepository;
import com.example.booksearch.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 유저 토큰 관리
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    UserTokenRepository userTokenRepository;

    public String create(String userId, Date date) {
        try {
            String token = JWT.create()
                    .withIssuer(JwtInfo.ISSUER)
                    .withClaim("id", userId)
                    .withExpiresAt(date)
                    .sign(JwtInfo.getAlgorithm());

            userTokenRepository.save(new UserToken(userId,token));
            return token;
        } catch (JWTCreationException createEx) {
            throw new UserException("ERROR[1009]");
        }
    }

    public Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(JwtInfo.getAlgorithm()).build();
            verifier.verify(token);

            return Boolean.TRUE;
        } catch (JWTVerificationException verifyEx) {
            throw new UserException("UNAUTHORIZED");
        }
    }

    public String getUserId(String token) {
        try {
            return JWT.decode(token).getClaim("id").asString();
        } catch (JWTDecodeException decodeEx) {
            throw new UserException("UNAUTHORIZED");
        }
    }


}
