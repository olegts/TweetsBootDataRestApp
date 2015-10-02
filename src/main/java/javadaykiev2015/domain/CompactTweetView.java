package javadaykiev2015.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * @author Oleg Tsal-Tsalko
 */
@Projection(name="compact", types={Tweet.class})
public interface CompactTweetView {
    String getId();
    String getText();
    long getFavoriteCount();
    long getRetweetCount();
    @Value("#{target.user.screenName}")
    String getUserName();
    @Value("#{target.user.tweetsCount}")
    long getUserTweetsCount();
    @Value("#{target.user.followersCount}")
    long getUserFollowersCount();
    @Value("#{target.user.friendsCount}")
    long getUserFriendsCount();
}
