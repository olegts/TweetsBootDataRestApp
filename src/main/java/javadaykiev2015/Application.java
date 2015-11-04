package javadaykiev2015;

import com.mongodb.Mongo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

/**
 * @author Oleg Tsal-Tsalko
 */
@SpringBootApplication
public class Application {

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo(), "tweets_store");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
