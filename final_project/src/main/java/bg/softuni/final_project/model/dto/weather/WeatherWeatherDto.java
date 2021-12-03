package bg.softuni.final_project.model.dto.weather;

import com.google.gson.annotations.Expose;

public class WeatherWeatherDto {
    @Expose
    private long id;
    @Expose
    private String main;
    @Expose
    private String description;
    @Expose
    private String icon;

    public WeatherWeatherDto() {
    }

    public WeatherWeatherDto(long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public long getId() {
        return id;
    }

    public WeatherWeatherDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getMain() {
        return main;
    }

    public WeatherWeatherDto setMain(String main) {
        this.main = main;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public WeatherWeatherDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public WeatherWeatherDto setIcon(String icon) {
        this.icon = icon;
        return this;
    }
}
