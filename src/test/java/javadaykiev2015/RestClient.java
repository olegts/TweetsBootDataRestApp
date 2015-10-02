package javadaykiev2015;

import com.fasterxml.jackson.databind.JsonNode;
import javadaykiev2015.domain.Tweet;
import org.junit.Test;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oleg Tsal-Tsalko
 */
public class RestClient {

    private final RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testSearchMethodWithoutContentTypeSpecifiedWhichReturnsHalDocumentByDefault() throws Exception {
        JsonNode jsonHalResponse = restTemplate.execute(
                "http://localhost:8080/tweets/search/byUser?user=tsaltsol",
                HttpMethod.GET,
                clientHttpRequest -> clientHttpRequest.getHeaders().clear(),
                new HttpMessageConverterExtractor<>(JsonNode.class, restTemplate.getMessageConverters()));
        assertThat(jsonHalResponse.at("/_embedded/tweets/0/userName").asText(), is("tsaltsol"));
    }

    @Test
    public void testSearchMethodWithContentTypeSpecifiedAsJsonWhichReturnsDataInCompactJsonFormat() throws Exception {
        JsonNode jsonResponse = restTemplate.getForObject("http://localhost:8080/tweets/search/byUser?user=tsaltsol", JsonNode.class);
        String resourceLink = jsonResponse.at("/links/0/href").asText();
        Tweet tweet = restTemplate.getForObject(resourceLink, Tweet.class);
        System.out.println(tweet);
    }

}