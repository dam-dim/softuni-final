package bg.softuni.final_project.web.schedular;

import bg.softuni.final_project.service.WeatherService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ApplicationScheduler {
    private final WeatherService weatherService;

    public ApplicationScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(cron = "@hourly")
    public void writeWeatherToJson() {
        weatherService.writeWeatherToJson();
    }

}
