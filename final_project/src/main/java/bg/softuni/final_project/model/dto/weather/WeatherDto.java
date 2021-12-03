package bg.softuni.final_project.model.dto.weather;

import com.google.gson.annotations.Expose;

public class WeatherDto {
    @Expose
    private long dt;
    @Expose
    private WeatherMainDto main;
    @Expose
    private WeatherWeatherDto[] weather;
    @Expose
    private WeatherCloudsDto clouds;
    @Expose
    private WeatherWindDto wind;
    @Expose
    private String dt_txt;

    public WeatherDto() {
    }

    public WeatherDto(long dt,
                      WeatherMainDto main,
                      WeatherWeatherDto[] weather,
                      WeatherCloudsDto clouds,
                      WeatherWindDto wind,
                      String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.clouds = clouds;
        this.wind = wind;
        this.dt_txt = dt_txt;
    }

    public long getDt() {
        return dt;
    }

    public WeatherDto setDt(long dt) {
        this.dt = dt;
        return this;
    }

    public WeatherMainDto getMain() {
        return main;
    }

    public WeatherDto setMain(WeatherMainDto main) {
        this.main = main;
        return this;
    }

    public WeatherWeatherDto[] getWeather() {
        return weather;
    }

    public WeatherDto setWeather(WeatherWeatherDto[] weather) {
        this.weather = weather;
        return this;
    }

    public WeatherCloudsDto getClouds() {
        return clouds;
    }

    public WeatherDto setClouds(WeatherCloudsDto clouds) {
        this.clouds = clouds;
        return this;
    }

    public WeatherWindDto getWind() {
        return wind;
    }

    public WeatherDto setWind(WeatherWindDto wind) {
        this.wind = wind;
        return this;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public WeatherDto setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
        return this;
    }
}
