package javadaykiev2015;

import javadaykiev2015.repository.TweetRepository;
import javadaykiev2015.validator.CreateTweetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * @author Oleg Tsal-Tsalko
 */
@Component
public class Initializer implements CommandLineRunner{

    @Autowired
    TweetRepository repository;

    @Autowired
    ValidatingRepositoryEventListener validatingRepositoryEventListener;

    @Override
    public void run(String... strings) throws Exception {

        validatingRepositoryEventListener.addValidator("beforeCreate", new CreateTweetValidator());

        /*repository.save(aTweet()
                .withId("123")
                .withText("All Blacks won!")
                .withLang("en")
                .withCreateTime(ZonedDateTime.now())
                .withUser(aUser()
                        .withId("1")
                        .withName("Oleg")
                        .withScreenName("tsaltsol")
                        .withTweetsCount(1)
                        .withLocation("Kiev")
                        .withCreateTime(ZonedDateTime.now())
                        .withURL(new URL("http://localhost:8080/tweets"))
                        .build())
                .build());
        Tweet tweet = repository.findOne("123");
        System.out.println(tweet);*/
    }
}
