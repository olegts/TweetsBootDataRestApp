package javadaykiev2015.mongo.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Oleg Tsal-Tsalko
 */
public class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(Date d) {
        return ZonedDateTime.ofInstant(d.toInstant(), ZoneId.of("UTC"));
    }
}
