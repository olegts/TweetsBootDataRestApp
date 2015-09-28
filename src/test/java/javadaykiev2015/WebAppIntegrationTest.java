package javadaykiev2015;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javadaykiev2015.domain.Tweet;
import javadaykiev2015.repository.TweetRepository;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Oleg Tsal-Tsalko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:7777")
@TestPropertySource("/test.properties")
public class WebAppIntegrationTest {

    @Autowired
    TweetRepository repository;

    RestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders requestHeaders = new HttpHeaders();

    @Before
    public void setUp() throws Exception {
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        requestHeaders.setContentType(mediaType);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }

    @Test
    public void testSaveAndLoadOperation() throws Exception {
        HttpEntity<String> newTweet = httpJsonRequestFrom("tweet.json");
        URI location = restTemplate.postForLocation("http://localhost:7777/tweets", newTweet, Object.class);
        assertThat(location.toString(), is("http://localhost:7777/tweets/643115913563217921"));

        Tweet tweet = restTemplate.getForObject(location, Tweet.class);
        assertThat(tweet.text, containsString("Murex Stage"));
        assertThat(tweet.user.name, is("Stage_Banque_Finance"));
        System.out.println(tweet);

        restTemplate.headForHeaders(location);
        restTemplate.delete(location);
        try {
            restTemplate.headForHeaders(location);
            fail("404 Http Response should have been returned resulted in HttpClientErrorException exception thrown from RestTemplate");
        }catch(HttpClientErrorException ex){
            //This is expected!
        }
    }

    @Test
    public void testSearchByUserName() throws Exception {
        restTemplate.postForLocation("http://localhost:7777/tweets", httpJsonRequestFrom("tweet.json"), Object.class);

        JsonNode node = restTemplate.getForObject("http://localhost:7777/tweets/search/byUser?user=Stage_Banque_Finance", JsonNode.class);
        assertThat(node.at("/links/0/href").asText(), is("http://localhost:7777/tweets/643115913563217921"));
    }

    @Test
    public void testUpdateOperation() throws Exception {
        HttpEntity<String> newTweet = httpJsonRequestFrom("tweet.json");
        URI location = restTemplate.postForLocation("http://localhost:7777/tweets", newTweet, Object.class);

        restTemplate.put(location, httpJsonRequestFrom(new Tweet("123", "Updated tweet", null)));

        Tweet tweet = restTemplate.getForObject(location, Tweet.class);
        assertThat(tweet.text, containsString("Updated tweet"));
        assertThat(tweet.text, not(containsString("Murex Stage")));
    }

    private HttpEntity<String> httpJsonRequestFrom(Object object) throws IOException {
        String json = new ObjectMapper().writeValueAsString(object);
        return new HttpEntity<>(json, requestHeaders);
    }

    private HttpEntity<String> httpJsonRequestFrom(String jsonFile) throws IOException {
        String json = IOUtils.toString(WebAppIntegrationTest.class.getClassLoader().getResourceAsStream(jsonFile), Charset.forName("UTF-8"));
        return new HttpEntity<>(json, requestHeaders);
    }

    @After
    public void cleanResources() throws Exception {
        repository.deleteAll();
    }
}
