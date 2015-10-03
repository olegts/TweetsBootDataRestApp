package javadaykiev2015.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javadaykiev2015.domain.Tweet;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.net.URL;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;

import static javadaykiev2015.domain.builder.TweetBuilder.aTweet;
import static javadaykiev2015.domain.builder.UserBuilder.aUser;

/**
 * @author Oleg Tsal-Tsalko
 */
public class JacksonTransformTest {

    ObjectMapper mapper = new ObjectMapper()
            .configure(SerializationFeature.INDENT_OUTPUT, true)
            .registerModule(new JavaTimeModule());

    @Test
    public void testMarshallingJava8DateTime() throws Exception {
        Tweet t = aTweet()
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
                .build();

        System.out.println(mapper.writeValueAsString(t));
    }


    @Test
    public void testUnmarshalling() throws Exception {
        String json = IOUtils.toString(JacksonTransformTest.class.getClassLoader().getResourceAsStream("tweet.json"), Charset.forName("UTF-8"));

        System.out.println(mapper.readValue(json, Tweet.class));
    }

}
