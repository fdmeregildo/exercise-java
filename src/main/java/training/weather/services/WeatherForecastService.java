package training.weather.services;

import java.util.Date;

public interface WeatherForecastService {

    String getCityWeather(String city, Date date);
}
