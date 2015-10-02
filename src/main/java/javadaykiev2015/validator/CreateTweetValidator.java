package javadaykiev2015.validator;

import javadaykiev2015.domain.Tweet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Oleg Tsal-Tsalko
 */
public class CreateTweetValidator implements Validator{

    static final Logger LOG = LoggerFactory.getLogger(CreateTweetValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == Tweet.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        LOG.info("Inside validator");
        Tweet t = (Tweet)o;
        if (StringUtils.isEmpty(t.id)){
            errors.rejectValue("id", "empty", "Tweet id shouldn't be null or empty");
        }
        if (StringUtils.isEmpty(t.text)){
            errors.rejectValue("text", "empty", "Tweet text shouldn't be null or empty");
        }
    }
}
