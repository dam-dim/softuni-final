package bg.softuni.final_project.model.dto.weather;

import com.google.gson.annotations.Expose;

public class WeatherMainDto {
    @Expose
    private double temp;

    public WeatherMainDto() {
    }

    public WeatherMainDto(double temp) {
        this.temp = temp;
    }

    public double getTemp() {
        return temp;
    }

    public WeatherMainDto setTemp(double temp) {
        this.temp = temp;
        return this;
    }
}
