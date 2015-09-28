package javadaykiev2015.event.handler;

import javadaykiev2015.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * @author Oleg Tsal-Tsalko
 */
@Component
@RepositoryEventHandler(Tweet.class)
public class TweetEventHandler {

    static final Logger LOG = LoggerFactory.getLogger(TweetEventHandler.class);

    @HandleBeforeCreate
    public void onTweetCreate(Tweet tweet){
        LOG.info("About to create Tweet:"+tweet);
    }

    @HandleBeforeSave
    public void onTweetUpdate(Tweet tweet){
        LOG.info("About to update Tweet:"+tweet);
    }

    @HandleBeforeDelete
    public void onTweetDelete(Tweet tweet){
        LOG.info("About to delete Tweet:"+tweet);
    }
}
