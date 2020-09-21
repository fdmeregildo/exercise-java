package training.weather.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ConsolidatedWeatherResponse implements Serializable {

    private String id;
    private String applicable_date;
    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_speed;
    private String wind_direction;
    private String wind_direction_compass;
    private String min_temp;
    private String max_temp;
    private String the_temp;
    private String air_pressure;
    private String humidity;
    private String visibility;
    private String predictability;

}
