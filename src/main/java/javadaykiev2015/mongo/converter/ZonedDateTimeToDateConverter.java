package javadaykiev2015.mongo.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Oleg Tsal-Tsalko
 */
public class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
    @Override
    public Date convert(ZonedDateTime zonedDateTime) {
        return Date.from(zonedDateTime.toInstant());
    }
}
