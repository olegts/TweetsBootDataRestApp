package javadaykiev2015.domain.builder;

import javadaykiev2015.domain.User;

import java.net.URL;
import java.time.ZonedDateTime;

/**
 * @author Oleg Tsal-Tsalko
 */
public final class UserBuilder {
    private User user;

    private UserBuilder() {
        user = new User();
    }

    public static UserBuilder aUser(){
        return new UserBuilder();
    }

    public UserBuilder withId(String id){
        user.id = id;
        return this;
    }

    public UserBuilder withName(String name){
        user.name = name;
        return this;
    }

    public UserBuilder withScreenName(String name){
        user.screenName = name;
        return this;
    }

    public UserBuilder withTweetsCount(long tweetsCount){
        user.tweetsCount = tweetsCount;
        return this;
    }

    public UserBuilder withFavouritesCount(long favouritesCount){
        user.favouritesCount = favouritesCount;
        return this;
    }

    public UserBuilder withFollowersCount(long followersCount){
        user.followersCount = followersCount;
        return this;
    }

    public UserBuilder withFriendsCount(long friendsCount){
        user.friendsCount = friendsCount;
        return this;
    }

    public UserBuilder withDescription(String description){
        user.description = description;
        return this;
    }

    public UserBuilder withLocation(String location){
        user.location = location;
        return this;
    }

    public UserBuilder withURL(URL url){
        user.url = url;
        return this;
    }

    public UserBuilder withCreateTime(ZonedDateTime createTime){
        user.createTime = createTime;
        return this;
    }

    public User build(){
        return user;
    }    
}
