package javadaykiev2015.mongo.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;

/**
 * @author Oleg Tsal-Tsalko
 */
public class StringToZonedDateTimeConverter implements Converter<String, ZonedDateTime> {
    @Override
    public ZonedDateTime convert(String s) {
        return ZonedDateTime.parse(s);
    }
}
