package javadaykiev2015;

import javadaykiev2015.domain.Tweet;
import javadaykiev2015.repository.TweetRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static javadaykiev2015.domain.builder.TweetBuilder.aTweet;
import static javadaykiev2015.domain.builder.UserBuilder.aUser;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oleg Tsal-Tsalko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
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
                aUser().withName("Oleg").build()).build();
        repository.save(t);

        List<Tweet> tweets = repository.findByUserName("Oleg");
        assertThat(tweets.size(), is(1));
    }

    @After
    public void cleanResources() throws Exception {
        repository.deleteAll();
    }
}
