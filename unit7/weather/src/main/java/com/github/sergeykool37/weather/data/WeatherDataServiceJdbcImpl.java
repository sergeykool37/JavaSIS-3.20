package com.github.sergeykool37.weather.data;

import com.github.sergeykool37.weather.dto.RecordDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

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
    public List<RecordDTO> findAll() {
        String sql="SELECT  date,city,temp FROM weather";
        return jdbcTemplate.query(sql,
                ((rs, rowNum) -> new RecordDTO(

                                 rs.getDate("date").toLocalDate().
                                        format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)).toString()
                                , rs.getString("city")
                                , rs.getString("temp"))));
    }
}
