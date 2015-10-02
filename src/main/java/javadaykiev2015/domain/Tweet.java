package javadaykiev2015.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.Identifiable;

import java.time.ZonedDateTime;

/**
 * @author: Oleg Tsal-Tsalko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "tweets")
public class Tweet implements Identifiable<String>{

    @Id
    public String id;
    public String text;
    public String lang;

    @JsonProperty("favorite_count")
    public long favoriteCount;
    @JsonProperty("retweet_count")
    public long retweetCount;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd kk:mm:ss Z yyyy")
    @JsonProperty("created_at")
    public ZonedDateTime createTime;

    public User user;

    public Tweet() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tweet)) return false;

        Tweet tweet = (Tweet) o;

        return !(id != null ? !id.equals(tweet.id) : tweet.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", favoriteCount=" + favoriteCount +
                ", lang='" + lang + '\'' +
                ", retweetCount=" + retweetCount +
                ", createTime=" + createTime +
                ", user=" + user +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }
}
