package javadaykiev2015.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * @author: Oleg Tsal-Tsalko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public String id;
    public String name;
    @JsonProperty("screen_name")
    public String screenName;

    @JsonProperty("statuses_count")
    public long tweetsCount;
    @JsonProperty("favourites_count")
    public long favouritesCount;
    @JsonProperty("followers_count")
    public long followersCount;
    @JsonProperty("friends_count")
    public long friendsCount;

    public String description;
    public String location;
    public URL url;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd kk:mm:ss Z yyyy")
    @JsonProperty("created_at")
    public ZonedDateTime createTime;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return !(id != null ? !id.equals(user.id) : user.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", tweetsCount=" + tweetsCount +
                ", favouritesCount=" + favouritesCount +
                ", followersCount=" + followersCount +
                ", friendsCount=" + friendsCount +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", url=" + url +
                ", createTime=" + createTime +
                '}';
    }
}
