package com.github.sergeykool37.weather.web.impl;

import com.github.sergeykool37.weather.web.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner
                .SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner
                .SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class WeatherServiceRestImplTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    void getWeather() {
        String weather=weatherService.getWeather("Krasnoyarsk");
        System.out.println(weather);
    }
}