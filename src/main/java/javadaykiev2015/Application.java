package javadaykiev2015;

import com.mongodb.Mongo;
import javadaykiev2015.mongo.converter.StringToZonedDateTimeConverter;
import javadaykiev2015.mongo.converter.ZonedDateTimeToStringConverter;
import javadaykiev2015.validator.CreateTweetValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.util.Arrays;

/**
 * @author Oleg Tsal-Tsalko
 */
@SpringBootApplication
public class Application {

    @Value("${mongo.db}")
    String mongoDB;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo(), mongoDB);
    }

    @Bean
    public CustomConversions mongoConverters(){
        return new CustomConversions(Arrays.asList(
                new ZonedDateTimeToStringConverter(),
                new StringToZonedDateTimeConverter()));
    }

    //This doesn't because of existing BUG - https://jira.spring.io/browse/DATAREST-524
    /*@Bean
    public CreateTweetValidator beforeCreateTweetValidator() {
        return new CreateTweetValidator();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
