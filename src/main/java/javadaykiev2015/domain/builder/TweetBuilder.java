package javadaykiev2015.domain.builder;

import javadaykiev2015.domain.Tweet;
import javadaykiev2015.domain.User;

import java.time.ZonedDateTime;

/**
 * @author Oleg Tsal-Tsalko
 */
public final class TweetBuilder {

    private Tweet tweet;

    private TweetBuilder() {
        tweet = new Tweet();
    }

    public static TweetBuilder aTweet(){
        return new TweetBuilder();
    }

    public TweetBuilder withId(String id){
        tweet.id = id;
        return this;
    }

    public TweetBuilder withText(String text){
        tweet.text = text;
        return this;
    }

    public TweetBuilder withFavoriteCount(long favoriteCount){
        tweet.favoriteCount = favoriteCount;
        return this;
    }

    public TweetBuilder withLang(String lang){
        tweet.lang = lang;
        return this;
    }

    public TweetBuilder withRetweetCount(long retweetCount){
        tweet.retweetCount = retweetCount;
        return this;
    }

    public TweetBuilder withCreateTime(ZonedDateTime createTime){
        tweet.createTime = createTime;
        return this;
    }

    public TweetBuilder withUser(User user){
        tweet.user = user;
        return this;
    }

    public Tweet build(){
        return tweet;
    }
}
