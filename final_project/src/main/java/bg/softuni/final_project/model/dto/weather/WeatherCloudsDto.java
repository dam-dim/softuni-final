package bg.softuni.final_project.model.dto.weather;

import com.google.gson.annotations.Expose;

public class WeatherCloudsDto {
    @Expose
    private int all;

    public WeatherCloudsDto() {
    }

    public WeatherCloudsDto(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public WeatherCloudsDto setAll(int all) {
        this.all = all;
        return this;
    }
}
