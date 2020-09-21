package training.weather.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil {

    private final static Logger logger = Logger.getLogger(DateUtil.class.getName());

    public static String format(Date date, String pattern){

        String dateFormat = "";

        if(date !=null){

            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            dateFormat = sdf.format(date);
        }

        return dateFormat;
    }

    public static Date format(String date, String pattern){

        Date dateFormat = null;

        if(date !=null){

            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                dateFormat = sdf.parse(date);

            }catch (Exception ex) {

            }
        }

        return dateFormat;
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
