package com.example.booksearch.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.api.kakao")
@Data
@Component
public class KakaoApiProperties {
    private String domain;
    private String bookSearchApi;
    private String apiKey;

}
