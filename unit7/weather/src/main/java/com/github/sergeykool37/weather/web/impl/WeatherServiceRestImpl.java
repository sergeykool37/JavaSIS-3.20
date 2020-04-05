package com.github.sergeykool37.weather.web.impl;

import com.github.sergeykool37.weather.dto.WeatherDTO;
import com.github.sergeykool37.weather.web.WeatherService;
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

    @Override
    public String getWeather(String city) {
        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        String URL= String.format("https://community-open-weather-map.p.rapidapi.com/weather?units=metric&mode=json&q=%s", city);
        headers.add("x-rapidapi-host","community-open-weather-map.p.rapidapi.com");
        headers.add("x-rapidapi-key","cdd96ad95amsheebcca53e65aac1p136010jsn18e30e4e6350");
        HttpEntity entity=new HttpEntity(headers);
        ResponseEntity<WeatherDTO> result=restTemplate.exchange(URL,HttpMethod.GET,entity, WeatherDTO.class,headers);
        return  result.getBody().getMain().getTemp();
    }
}

