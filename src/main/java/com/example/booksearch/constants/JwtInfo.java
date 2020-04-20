package com.example.booksearch.constants;

import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

public abstract class JwtInfo {

	public static final String HEADER_NAME = "Authorization";

	public static final String ISSUER = "issuer";

	public static final String TOKEN_KEY = "tempkey.booksearch";

	public static final long EXPIRES_LIMIT = 3L;

	public static Algorithm getAlgorithm() {
		try {
			return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
		} catch (IllegalArgumentException e) {
			return Algorithm.none();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Algorithm.none();
		}
	}
}
