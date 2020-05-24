package com.github.sergeykool37.weather.dto;

import java.time.LocalDate;

public class RecordDTO {
    final  String WeatherDate;
    final String city;
    final String temp;


    public RecordDTO(String weatherDate, String city, String temp) {
        this.WeatherDate = weatherDate;
        this.city = city;
        this.temp = temp;
    }
    public String getWeatherDate(){
        return WeatherDate;
    }
    public String getCity(){
        return city;
    }
    public String getTemp(){
        return temp;
    }
}
//    ormat("%s %s %s"
//                  , rs.getDate("date").toLocalDate().
//        format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
//        , rs.getString("city")
//        , rs.getString("temp"))));