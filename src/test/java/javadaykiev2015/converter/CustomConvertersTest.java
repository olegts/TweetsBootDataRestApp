package javadaykiev2015.converter;

import javadaykiev2015.mongo.converter.DateToZonedDateTimeConverter;
import javadaykiev2015.mongo.converter.ZonedDateTimeToDateConverter;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Oleg Tsal-Tsalko
 */
public class CustomConvertersTest {
    @Test
    public void shouldConvertFromDateToZonedDateTime() throws Exception {
        Date date = new Date();
        System.out.println("Date:["+date+"]");
        ZonedDateTime zonedDateTime = new DateToZonedDateTimeConverter().convert(date);
        System.out.println("ZonedDateTime:["+zonedDateTime+"]");
    }

    @Test
    public void shouldConvertFromZonedDateTimeToDate() throws Exception {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("ZonedDateTime:["+zonedDateTime+"]");
        Date date = new ZonedDateTimeToDateConverter().convert(zonedDateTime);
        System.out.println("Date:["+date+"]");
    }
}
