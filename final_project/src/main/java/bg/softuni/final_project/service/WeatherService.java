package bg.softuni.final_project.service;

import bg.softuni.final_project.model.dto.weather.WeatherDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WeatherService {
    WeatherDto[] getWeatherDto();

    void writeWeatherToJson();

    List<List<WeatherDto>> groupWeatherDto();
}
