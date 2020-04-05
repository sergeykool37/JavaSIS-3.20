package com.github.sergeykool37.weather.data;

import java.util.List;

public interface WeatherDataService {
    void save(String weather,String city);
    List<String> findAll();
}
