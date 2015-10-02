package javadaykiev2015;

import javadaykiev2015.domain.Tweet;
import javadaykiev2015.repository.TweetRepository;
import javadaykiev2015.validator.CreateTweetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.ZonedDateTime;

import static javadaykiev2015.domain.builder.TweetBuilder.aTweet;
import static javadaykiev2015.domain.builder.UserBuilder.aUser;

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

        if (!repository.exists("123")) {
            repository.save(aTweet()
                    .withId("123")
                    .withText("All Blacks won!")
                    .withLang("en")
                    .withCreateTime(ZonedDateTime.now())
                    .withUser(aUser()
                            .withId("1")
                            .withName("Oleg")
                            .withScreenName("tsaltsol")
                            .withTweetsCount(2)
                            .withFriendsCount(41)
                            .withFollowersCount(74)
                            .withLocation("Kiev")
                            .withCreateTime(ZonedDateTime.now())
                            .withURL(new URL("http://localhost:8080/tweets"))
                            .build())
                    .build());
        }
        if (!repository.exists("777")) {
            repository.save(aTweet()
                    .withId("777")
                    .withText("JavaDay Kiev 2015 is comming")
                    .withLang("en")
                    .withCreateTime(ZonedDateTime.now())
                    .withUser(aUser()
                            .withId("1")
                            .withName("Oleg")
                            .withScreenName("tsaltsol")
                            .withTweetsCount(2)
                            .withFriendsCount(41)
                            .withFollowersCount(74)
                            .withLocation("Kiev")
                            .withCreateTime(ZonedDateTime.now())
                            .withURL(new URL("http://localhost:8080/tweets"))
                            .build())
                    .build());
        }
        Tweet tweet = repository.findOne("123");
        System.out.println(tweet);
    }
}
