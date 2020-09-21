package training.weather.controller;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import training.weather.services.WeatherForecastService;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastControllerTest {


    @MockBean
    WeatherForecastService weatherForecastService;


    @Before
    public void setUp(){

        when(weatherForecastService.getCityWeather(anyString(), any(Date.class))).thenReturn("Showers");
    }

    @Test
    public void geCityWeather_HttpStatusOk(){

        RestAssured.get("/weather/Madrid").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void geCityWeather_returnValueOk(){

       String weatherForecast = RestAssured.get("/weather/Madrid").getBody().asString();
       Assert.assertNotNull(weatherForecast);
    }

    @Test
    public void geCityWeatherDayTest_HttpStatusOk(){

        RestAssured.get("/weather/Madrid/20/10/2020").then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void geCityWeatherDayTest_returnValueOk(){

        String weatherForecast = RestAssured.get("/weather/Madrid/20/10/2020").getBody().asString();
        Assert.assertNotNull(weatherForecast);
    }
}
