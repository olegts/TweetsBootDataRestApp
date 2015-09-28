package javadaykiev2015.monitoring;

import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Oleg Tsal-Tsalko
 */
@Component
public class DBHealth implements HealthIndicator{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Health health() {
        try {
            DBCollection collection = mongoTemplate.getCollection("tweets");
            return Health.up().withDetail("tweetsCount", collection.count()).build();
        }catch(RuntimeException ex){
            return Health.down(ex).build();
        }
    }
}
