package training.weather.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import training.weather.dto.ConsolidatedWeatherResponse;
import training.weather.dto.LocationResponse;
import training.weather.dto.LocationSearchResponse;
import training.weather.resources.MetaWeatherClient;
import training.weather.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastServiceImplTest {

    private final static Logger logger = Logger.getLogger(SpringRunner.class.getName());

    @MockBean
    MetaWeatherClient metaWeatherClient;

    @Autowired
    WeatherForecastService weatherForecastService;

    @Before
    public void setUp(){
        LocationSearchResponse locationSearchResponse = new LocationSearchResponse();
        locationSearchResponse.setWoeid("766273");

        when(metaWeatherClient.getLocationByCityName(anyString())).thenReturn(locationSearchResponse);

        List<ConsolidatedWeatherResponse> consolidatedWeatherResponseList = new ArrayList<>();
        ConsolidatedWeatherResponse consolidatedWeatherResponse = new ConsolidatedWeatherResponse();
        consolidatedWeatherResponse.setWeather_state_name("Showers");
        consolidatedWeatherResponse.setApplicable_date("2020-08-20");
        consolidatedWeatherResponseList.add(consolidatedWeatherResponse);

        LocationResponse locationResponse = new LocationResponse();
        locationResponse.setConsolidated_weather(consolidatedWeatherResponseList);

        when(metaWeatherClient.getLocation(locationSearchResponse.getWoeid())).thenReturn(locationResponse);
    }

    @Test
    public void getCityWeather_Ok(){

        String weatherForecast = weatherForecastService
                .getCityWeather("Madrid", DateUtil.toDate(LocalDate.of(2020, 8, 20)));
        logger.info("weatherForecast: " + weatherForecast);

        Assert.assertEquals("Showers", weatherForecast);

    }

    @Test
    public void getCityWeather_moreThanMaximumDaysShouldReturnEmpty(){

        String weatherForecast = weatherForecastService
                .getCityWeather("Madrid", DateUtil.toDate(LocalDate.of(2020, 8, 20).plusDays(7)));
        logger.info("weatherForecast: " + weatherForecast);

        Assert.assertEquals("", weatherForecast);
    }

    @Test
    public void getCityWeather_dateNotFoundShouldReturnEmpty(){

        String weatherForecast = weatherForecastService
                .getCityWeather("Madrid", DateUtil.toDate(LocalDate.of(2019, 8, 8).plusDays(7)));
        logger.info("weatherForecast: " + weatherForecast);

        Assert.assertEquals("", weatherForecast);
    }
}
