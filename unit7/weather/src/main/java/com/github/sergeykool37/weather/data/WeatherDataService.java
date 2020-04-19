package com.github.sergeykool37.weather.data;

import com.github.sergeykool37.weather.dto.RecordDTO;

import java.util.List;

public interface WeatherDataService {
    void save(String weather,String city);
    List<RecordDTO> findAll();
}
