package bg.softuni.final_project.model.dto.weather;

import com.google.gson.annotations.Expose;

public class WeatherWindDto {
    @Expose
    private double speed;
    @Expose
    private double deg;
    @Expose
    private double gust;

    public WeatherWindDto() {
    }

    public WeatherWindDto(double speed, double deg, double gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    public double getSpeed() {
        return speed;
    }

    public WeatherWindDto setSpeed(double speed) {
        this.speed = speed;
        return this;
    }

    public double getDeg() {
        return deg;
    }

    public WeatherWindDto setDeg(double deg) {
        this.deg = deg;
        return this;
    }

    public double getGust() {
        return gust;
    }

    public WeatherWindDto setGust(double gust) {
        this.gust = gust;
        return this;
    }
}
