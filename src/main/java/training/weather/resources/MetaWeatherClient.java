package training.weather.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import training.weather.dto.ConsolidatedWeatherResponse;
import training.weather.dto.LocationResponse;
import training.weather.dto.LocationSearchResponse;
import training.weather.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class MetaWeatherClient {

    @Autowired
    RestTemplate restTemplate;


    public LocationSearchResponse getLocationByCityName(String city) {

        LocationSearchResponse[] locationSearchResponse =
                restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query="
                        + city, LocationSearchResponse[].class);

        return locationSearchResponse[0];
    }

    public LocationResponse getLocation(String cityId) {

        LocationResponse locationResponse = restTemplate.getForObject("https://www.metaweather.com/api/location/"
                + cityId + "/", LocationResponse.class);

        return locationResponse;
    }

    public List<ConsolidatedWeatherResponse> getLocationDay(String cityId, Date date) {

        String dateFormat = DateUtil.format(date, "yyyy/MM/dd");

        ConsolidatedWeatherResponse[] consolidatedWeatherResponses = restTemplate.getForObject("https://www.metaweather.com/api/location/"
                + cityId + "/" + dateFormat, ConsolidatedWeatherResponse[].class);

        return Arrays.asList(consolidatedWeatherResponses);
    }


    public RestTemplate getRestTemplate() {
        return restTemplate;
    }
}
