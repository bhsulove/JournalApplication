package com.edigest.journalApp.service;

import com.edigest.journalApp.api.response.QuoteResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Slf4j
@Component
public class QuoteService {
    @Autowired
    private RestTemplate restTemplate;

    public QuoteResponse getQuote(String category) {
        String url = "https://api.api-ninjas.com/v1/quotes?category=" + category;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Api-Key", "9nybB87Zz+JLKmM8/N+rzA==MAstP4HniAZy6zr4"); // Replace with your actual API key
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<QuoteResponse[]> exchange = restTemplate
                .exchange(url, HttpMethod.GET, entity, QuoteResponse[].class);
        return exchange.getBody()[0];

    }
}
