package bg.softuni.final_project.service.impl;

import bg.softuni.final_project.model.dto.weather.WeatherDto;
import bg.softuni.final_project.service.WeatherService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final Gson gson;

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);
    private static final String PRODUCTS_PATH = "src/main/resources/files/weather-export.json";
    private static final int CITY_ID = 726050;
    private static final String AUTH_ID = "3b6f820c751efb61d199e8b6650a5885";
    private static final String UNITS = "metric";
    private static final String URL = "https://api.openweathermap.org/data/2.5/forecast?id="+CITY_ID+"&units="+UNITS+"&appid="+AUTH_ID;

    public WeatherServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public WeatherDto[] getWeatherDto() {
        String content = "";

        try {
            content = String.join("", Files.readAllLines(Path.of(PRODUCTS_PATH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeatherDto[] data = gson.fromJson(content, WeatherDto[].class);

        return data;
    }

    @Override
    public void writeWeatherToJson(){
        BufferedReader reader;
        String line;
        StringBuilder responseContent = new StringBuilder();

        HttpURLConnection connection = null;

        try{
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);// 5000 milliseconds = 5 seconds
//            connection.setReadTimeout(5000);

            // Test if the response from the server is successful
            int status = connection.getResponseCode();

            if (status < 300) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
            System.out.println(responseContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }

        String data = responseContent.toString().split("\"list\":")[1].split("\"city\":")[0];
        String comaFreeDta = data.substring(0, data.length()-1);

        JsonElement element = new JsonParser().parse(comaFreeDta);

        String export = gson.toJson(element);

        File toExportTo = new File("src\\main\\resources\\files\\weather-export.json");
        try {
            toExportTo.createNewFile();
            FileWriter fileWriter = new FileWriter(toExportTo);
            fileWriter.write(export);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<List<WeatherDto>> groupWeatherDto() {
        WeatherDto[] data = getWeatherDto();
        List<List<WeatherDto>> toReturn = new ArrayList<>();
        List<WeatherDto> innerList = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            String currentDate = data[i].getDt_txt();
            String previousDate = i == 0 ? currentDate : data[i-1].getDt_txt();

            String currentDay = currentDate.split(" ")[0].split("-")[2];
            String previousDay = previousDate.split(" ")[0].split("-")[2];

            double temp = data[i].getMain().getTemp();
            data[i].getMain().setTemp(Math.round(temp));

            if (!currentDay.equals(previousDay)) {
                toReturn.add(innerList);
                innerList = new ArrayList<>();
            }

            innerList.add(data[i]);

            if (toReturn.size() == 4) {
                break;
            }
        }

        toReturn.add(innerList);

        return toReturn;
    }
}
