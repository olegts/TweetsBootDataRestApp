package javadaykiev2015;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${mongo.db}")
    String mongoDB;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new Mongo(), mongoDB);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
