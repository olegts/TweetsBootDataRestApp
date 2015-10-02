package javadaykiev2015.repository;

import javadaykiev2015.domain.Tweet;
import javadaykiev2015.web.projection.CompactTweetView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @author Oleg Tsal-Tsalko
 */
@RepositoryRestResource(collectionResourceRel = "tweets", path = "tweets", excerptProjection = CompactTweetView.class)
public interface TweetRepository extends MongoRepository<Tweet, String> {

    @RestResource(path = "allByUser", rel = "allByUser", exported = false)
    List<Tweet> findByUserScreenName(@Param("user") String userName);

    @RestResource(path = "byUser", rel = "byUser")
    List<Tweet> findByUserScreenName(@Param("user") String userName, Pageable pageable);

    @RestResource(path = "forMostFavorites", rel = "forMostFavorites")
    List<Tweet> findByFavoriteCountGreaterThanOrderByFavoriteCountDesc(@Param("greaterThan") int threshold, Pageable pageable);

    @RestResource(path = "forTextLike", rel = "forTextLike")
    List<Tweet> findByTextLikeOrderByCreateTimeDesc(@Param("text") String text, Pageable pageable);

    @RestResource(path = "usingCustomCriteria", rel = "usingCustomCriteria")
    @Query(value="{ 'user.screenName' : ?0 }", fields="{ 'text' : 1, 'user.screenName' : 1, 'createTime' : 1}")
    List<Tweet> findUsingCustomQuery(@Param("user") String user, Pageable pageable);
}