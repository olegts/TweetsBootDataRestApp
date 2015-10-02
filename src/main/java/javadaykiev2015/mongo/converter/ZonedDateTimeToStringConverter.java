package javadaykiev2015.mongo.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;

/**
 * @author Oleg Tsal-Tsalko
 */
public class ZonedDateTimeToStringConverter implements Converter<ZonedDateTime, String>{
    @Override
    public String convert(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toString();
    }
}
