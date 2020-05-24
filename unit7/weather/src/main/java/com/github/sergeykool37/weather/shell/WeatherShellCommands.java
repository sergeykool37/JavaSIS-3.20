package com.github.sergeykool37.weather.shell;

import com.github.sergeykool37.weather.data.WeatherDataService;
import com.github.sergeykool37.weather.web.WeatherService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Collection;
import java.util.stream.Collectors;

@ShellComponent
public class WeatherShellCommands {
    private final WeatherService weatherService;
    private final WeatherDataService weatherDataService;

    public WeatherShellCommands(WeatherService weatherService, WeatherDataService weatherDataService) {
        this.weatherService = weatherService;
        this.weatherDataService = weatherDataService;
    }
    private String lastTemp,lastCity;

    @ShellMethod("Get weather")
    public String weather(@ShellOption(defaultValue = "Красноярск") String city){
        String weather=weatherService.getWeather(city);
        lastCity=city;
        lastTemp=weather;
        return weather;
    }

    @ShellMethod("Save weather")
    public  String save(){
        if (lastTemp==null){return "Сначала загрузите погоду";}
        else {
            weatherDataService.save(lastTemp,lastCity);
            return "Запись сохранена";
        }
    }
    @ShellMethod("Show all records")
    public String show(){
        return weatherDataService.findAll().stream()
                .map(data->String.format("%s, %s, %s",data.getWeatherDate(),data.getCity(),data.getTemp()))
                .collect(Collectors.joining(System.lineSeparator()));

    }

}
