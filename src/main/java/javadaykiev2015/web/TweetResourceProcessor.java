package javadaykiev2015.web;

import javadaykiev2015.domain.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Oleg Tsal-Tsalko
 */
@Component
public class TweetResourceProcessor implements ResourceProcessor<Resource<Tweet>>{

    @Autowired
    private EntityLinks entityLinks;

    @Override
    public Resource<Tweet> process(Resource<Tweet> tweetResource) {
        Tweet tweet = tweetResource.getContent();
        tweetResource.add(entityLinks.linkForSingleResource(tweet).withRel("delete"));
        tweetResource.add(entityLinks.linkForSingleResource(tweet).withRel("update"));
        return tweetResource;
    }
}
