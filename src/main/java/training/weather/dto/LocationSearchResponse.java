package training.weather.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LocationSearchResponse implements Serializable {

    private String title;
    private String location_type;
    private String latt_long;
    private String woeid;
    private String distance;


}
