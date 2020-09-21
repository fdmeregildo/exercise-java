package training.weather.resources;

import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import training.weather.dto.ConsolidatedWeatherResponse;
import training.weather.dto.LocationResponse;
import training.weather.dto.LocationSearchResponse;
import training.weather.util.DateUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaWeatherClientTest {

    public final static Logger logger = Logger.getLogger(MetaWeatherClient.class.getName());

    @Autowired
    MetaWeatherClient metaWeatherClient;

    @Test(expected = HttpClientErrorException.class)
    public void getLocationSearchWithoutParametersException(){

       LocationSearchResponse location = metaWeatherClient.getLocationByCityName("");
    }

    @Test
    public void getLocationSearch_Ok(){

        LocationSearchResponse location = metaWeatherClient.getLocationByCityName("Madrid");
        logger.info(location.toString());

        Assert.assertNotNull(location);
    }

    @Test
    public void getLocationByCityId_Ok(){

        //id City (Madrid): 766273

        LocationResponse location = metaWeatherClient.getLocation("766273");
        logger.info(location.toString());

        Assert.assertNotNull(location);
    }

    @Test
    public void getLocationByCityIdAndDate_Ok(){


        List<ConsolidatedWeatherResponse> consolidateWeather = metaWeatherClient.getLocationDay("766273", DateUtil.toDate(LocalDate.now().plusDays(1)));
        logger.info(consolidateWeather.toString());

        Assert.assertThat(consolidateWeather, CoreMatchers.not(IsEmptyCollection.empty()));
    }


}
