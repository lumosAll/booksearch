package com.example.booksearch.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.api.naver")
@Data
@Component
public class NaverApiProperties {
    private String domain;
    private String bookSearchApi;
    private String clientId;
    private String clientSecret;

}
