package javadaykiev2015;

import javadaykiev2015.domain.Tweet;
import javadaykiev2015.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Oleg Tsal-Tsalko
 */
@Component
public class Initializer implements CommandLineRunner{

    @Autowired
    TweetRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        /*repository.save(new Tweet("123", "bla", null));
        repository.save(new Tweet(null, "foo", null));
        List<Tweet> tweets = repository.findAll();
        System.out.println(tweets);*/
    }
}
