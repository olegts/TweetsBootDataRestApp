package javadaykiev2015;

import javadaykiev2015.domain.Tweet;
import javadaykiev2015.repository.TweetRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.URL;
import java.time.ZonedDateTime;
import java.util.List;

import static javadaykiev2015.domain.builder.TweetBuilder.aTweet;
import static javadaykiev2015.domain.builder.UserBuilder.aUser;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Oleg Tsal-Tsalko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@TestPropertySource("/test.properties")
public class AppIntegrationTest {

    @Autowired
    TweetRepository repository;

    @Test
    public void testBasicCRUDOperations() throws Exception {
        Tweet t1 = aTweet().withId("123").withText("tweet1").build();
        Tweet t2 = aTweet().withText("tweet2").build();
        repository.save(t1);
        repository.save(t2);
        assertThat(repository.count(), is(2L));
        List<Tweet> tweets = repository.findAll();
        assertThat(tweets.size(), is(2));
        Tweet tweet = repository.findOne("123");
        assertThat(tweet, is(t1));
        assertThat(repository.exists("123"), is(true));
        repository.delete("123");
        assertThat(repository.count(), is(1L));
        assertThat(repository.exists("123"), is(false));
        repository.delete(t2);
        assertThat(repository.count(), is(0L));
    }

    @Test
    public void testFindByUserOperation() throws Exception {
        Tweet t = aTweet().withId("123").withText("hey").withUser(
                aUser().withScreenName("tsaltsol").build()).build();
        repository.save(t);

        List<Tweet> tweets = repository.findByUserScreenName("tsaltsol");
        assertThat(tweets.size(), is(1));
    }

    @Test
    public void testFindByFavoriteCountGreaterThanOrderByFavoriteCountDesc() throws Exception {
        Tweet t1 = aTweet().withId("123").withText("Java the best").withFavoriteCount(100).build();
        Tweet t2 = aTweet().withId("321").withText("Spring the best").withFavoriteCount(50).build();
        repository.save(t1);
        repository.save(t2);

        List<Tweet> tweets = repository.findByFavoriteCountGreaterThanOrderByFavoriteCountDesc(70, null);
        assertThat(tweets.size(), is(1));
        assertThat(tweets.get(0).id, is("123"));
        tweets = repository.findByFavoriteCountGreaterThanOrderByFavoriteCountDesc(30, null);
        assertThat(tweets.size(), is(2));
        //Order is important
        assertThat(tweets.get(0).id, is("123"));
        assertThat(tweets.get(1).id, is("321"));
    }

    @Test
    public void testFindByTextLikeOrderByCreateTimeDesc() throws Exception {
        Tweet t1 = aTweet().withId("321").withText("Java the best").withCreateTime(ZonedDateTime.now()).build();
        Tweet t2 = aTweet().withId("123").withText("JavaDay is comming").withCreateTime(ZonedDateTime.now().minusDays(1)).build();
        repository.save(t1);
        repository.save(t2);
        List<Tweet> tweets = repository.findByTextLikeOrderByCreateTimeDesc("Java", null);
        assertThat(tweets.size(), is(2));
        //Order is important
        assertThat(tweets.get(0).id, is("321"));
        assertThat(tweets.get(1).id, is("123"));
        tweets = repository.findByTextLikeOrderByCreateTimeDesc("java", null);
        assertThat(tweets.size(), is(0));
    }

    @Test
    public void testFindUsingCustomQuery() throws Exception {
        repository.save(aTweet()
                .withId("123")
                .withFavoriteCount(50)
                .withText("All Blacks won first game of 2015 WorldCup!")
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
        List<Tweet> tweets = repository.findUsingCustomQuery("tsaltsol", null);
        assertThat(tweets.size(), is(1));
        assertThat(tweets.get(0).text, is("All Blacks won first game of 2015 WorldCup!"));
        assertNull(tweets.get(0).user.name);
    }

    @Before
    @After
    public void cleanResources() throws Exception {
        repository.deleteAll();
    }
}
