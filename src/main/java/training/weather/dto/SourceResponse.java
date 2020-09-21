package training.weather.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SourceResponse implements Serializable {

    private String title;
    private String url;

}
