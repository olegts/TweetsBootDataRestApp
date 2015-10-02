package javadaykiev2015.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.mongodb.core.mapping.Field;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * @author: Oleg Tsal-Tsalko
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    public String id;
    public String name;
    @Field("screen_name")
    public String screenName;

    @Field("statuses_count")
    public long tweetsCount;
    @Field("favourites_count")
    public long favouritesCount;
    @Field("followers_count")
    public long followersCount;
    @Field("friends_count")
    public long friendsCount;

    public String description;
    public String location;
    public URL url;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="EEE MMM dd kk:mm:ss Z yyyy")
    @Field("created_at")
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
