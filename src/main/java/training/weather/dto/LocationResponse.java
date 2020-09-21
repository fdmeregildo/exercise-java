package training.weather.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LocationResponse implements Serializable {

    private String title;
    private String location_type;
    private String latt_long;
    private String time;
    private String sun_rise;
    private String sun_set;
    private String timezone_name;
    private LocationParentResponse parent;
    private List<ConsolidatedWeatherResponse> consolidated_weather;
    private List<SourceResponse> sources;

}
