package javadaykiev2015.repository;

import javadaykiev2015.domain.CompactTweetView;
import javadaykiev2015.domain.Tweet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
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

}