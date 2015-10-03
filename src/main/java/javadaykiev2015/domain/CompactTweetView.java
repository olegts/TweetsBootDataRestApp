package javadaykiev2015.domain;

import javadaykiev2015.domain.Tweet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.time.ZonedDateTime;

/**
 * @author Oleg Tsal-Tsalko
 */
@Projection(name="compact", types={Tweet.class})
public interface CompactTweetView {
    String getId();
    String getText();
    long getFavoriteCount();
    long getRetweetCount();
    ZonedDateTime getCreateTime();
    @Value("#{target.user.screenName}")
    String getUserName();
    @Value("#{target.user.tweetsCount}")
    long getUserTweetsCount();
    @Value("#{target.user.followersCount}")
    long getUserFollowersCount();
    @Value("#{target.user.friendsCount}")
    long getUserFriendsCount();
}
