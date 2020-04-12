package com.github.sergeykool37.weather.data;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WeatherDataServiceJdbcImpl implements WeatherDataService {
    private final JdbcTemplate jdbcTemplate;
    public WeatherDataServiceJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void save(String weather, String city) {
        String date=LocalDate.now().toString();
        jdbcTemplate.update("INSERT INTO weather (date,city,temp) VALUES (?,?,?)", date, city, weather);
    }

    @Override
    public List<String> findAll() {
        String sql="SELECT  date,city,temp FROM weather";
        return jdbcTemplate.query(sql,
                ((rs, rowNum) -> String.
                        format("%s %s %s"
                                , rs.getDate("date").toLocalDate().
                                        format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)).toString()
                                , rs.getString("city")
                                , rs.getString("temp"))));
    }
}
