package com.github.sergeykool37.weather.web.impl;

import com.github.sergeykool37.weather.dto.WeatherDTO;
import com.github.sergeykool37.weather.web.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceRestImpl implements WeatherService {
    private final RestTemplate restTemplate;

    public WeatherServiceRestImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${x-rapidapi-host}")
    private String host;
    @Value("${x-rapidapi-key}")
    private String keyAPI;
    @Value("${url}")
    private String url;


    @Override
    public String getWeather(String city) {
        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        String URL= String.format(url, city);
        headers.add("x-rapidapi-host",host);
        headers.add("x-rapidapi-key",keyAPI);
        HttpEntity entity=new HttpEntity(headers);
        ResponseEntity<WeatherDTO> result=restTemplate.exchange(URL,HttpMethod.GET,entity, WeatherDTO.class,headers);
        return  result.getBody().getMain().getTemp();
    }
}

