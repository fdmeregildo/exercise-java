package training.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import training.weather.services.WeatherForecastService;
import training.weather.util.DateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;

@RestController
@RequestMapping("/weather")
public class WeatherForecastController {

    private final static Logger logger = Logger.getLogger(WeatherForecastController.class.getName());

    @Autowired
    WeatherForecastService weatherForecastService;

    public WeatherForecastController(WeatherForecastService weatherForecastService){
        this.weatherForecastService = weatherForecastService;
    }

    @GetMapping("/{city}")
    @ResponseStatus(HttpStatus.OK)
    public String getCityWeather(@PathVariable String city){

        logger.info("City: "+city);
        String weatherForecast = weatherForecastService.getCityWeather(city, new Date());

        logger.info("weatherForecast: "+weatherForecast);
        return weatherForecast;
    }

    @GetMapping("/{city}/{day}/{month}/{year}")
    @ResponseStatus(HttpStatus.OK)
    public String getCityWeather(@PathVariable String city,
                                 @PathVariable String day,
                                 @PathVariable String month,
                                 @PathVariable String year){


        logger.info("City: "+city);
        logger.info("Date (dd/MM/yyyy): "+day+"/"+month+"/"+year);

        String weatherForecast = weatherForecastService.getCityWeather(city, DateUtil.format(day+"/"+month+"/"+year,"dd/MM/yyyy"));

        logger.info("weatherForecast: "+weatherForecast);
        return weatherForecast;
    }
}
