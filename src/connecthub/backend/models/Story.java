package connecthub.backend.models;

import java.time.Duration;
import java.time.LocalDateTime;
import static connecthub.backend.constants.Constants.EXPIRATION_AFTER_SET_HOURS;

public class Story extends Content implements Expirable {

    // Default constructor (required for Jackson deserialization)
    public Story() {}

    public Story(String contentId, String authorId, ContentData contentData) {
        super(contentId, authorId, contentData);
    }

    @Override
    public boolean isExpired() {
        return Duration.between(getTimestamp(), LocalDateTime.now()).toHours() >= EXPIRATION_AFTER_SET_HOURS;
    }
}
