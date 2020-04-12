package com.github.sergeykool37.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	@Autowired
	JdbcTemplate jdbcTemplate;
	@PostConstruct
	private  void createWeatherTable(){
		jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS weather (date TIMESTAMP(8),city VARCHAR(20),temp double(2))");
	}
}
