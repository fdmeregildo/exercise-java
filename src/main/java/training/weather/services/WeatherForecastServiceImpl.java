package training.weather.services;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.weather.dto.ConsolidatedWeatherResponse;
import training.weather.dto.LocationResponse;
import training.weather.dto.LocationSearchResponse;
import training.weather.resources.MetaWeatherClient;
import training.weather.util.DateUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class WeatherForecastServiceImpl implements WeatherForecastService {

    @Autowired
    MetaWeatherClient metaWeatherClient;

    @Override
    public String getCityWeather(String city, Date date) {

        String weatherName = "";

        Date dateMaximum = DateUtil.toDate(LocalDate.now().plusDays(6));

        if (date.before(dateMaximum)) {

            LocationSearchResponse locationSearchResponse = metaWeatherClient.getLocationByCityName(city);
            LocationResponse locationResponse = metaWeatherClient.getLocation(locationSearchResponse.getWoeid());

            for (ConsolidatedWeatherResponse consolidatedWeatherResponse : locationResponse.getConsolidated_weather()) {

                if (DateUtil.format(date, "yyyy-MM-dd").equals(consolidatedWeatherResponse.getApplicable_date())) {
                    weatherName = consolidatedWeatherResponse.getWeather_state_name();
                    break;
                }
            }
        }

        return weatherName;
    }
}
