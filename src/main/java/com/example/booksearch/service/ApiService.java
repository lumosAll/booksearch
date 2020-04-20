package com.example.booksearch.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
public abstract class ApiService {

    private static final int READ_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;
    private static final String DEFAULT_CHARSET = "UTF-8";

    protected RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    @PostConstruct
    public void init() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);
        restTemplate = new RestTemplate(factory);
    }

    abstract protected HttpHeaders getHttpHeaders();

    protected  <T> T get(String domain, String uri, Map<String, Object> request, Class<T> cls) {
        final HttpEntity<String> entity = new HttpEntity<String>(getHttpHeaders());

        String url = String.format("%s%s", domain, uri);
        url += "?";
        for (String key : request.keySet()) {
            url += key + "=" + request.get(key) + "&";
        }
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            log.info("ApiService.get statusCode : {}",response.getStatusCode().toString());
            log.info("ApiService.get response body : {}",response.getBody());

            if (StringUtils.isNotBlank(response.getBody())) {
                return gson.fromJson(response.getBody(), cls);
            }
        } catch (Exception e) {
            log.error("[ERROR] ApiService.get - {}", e);
        }
        return null;
    }
}
