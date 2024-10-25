package com.edigest.journalApp.service;

import com.edigest.journalApp.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeatherService {
    private static final String apiKey = "2234167b9a235c97c7c27973135e585c";

    public static final String API = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String finaAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate
                .exchange(finaAPI, HttpMethod.GET, null, WeatherResponse.class);
        log.info("Response status code {}",response.getStatusCode());
        WeatherResponse body = response.getBody();
        return body;
    }
}



