package connecthub.contentCreation.backend;

import java.time.Duration;
import java.time.LocalDateTime;

public class Story extends Content implements Expirable {
    private static final long EXPIRATION_AFTER_SET_HOURS = 24;

    public Story(String authorId, ContentData contentData) {
        super(authorId, contentData);
    }

    @Override
    public boolean isExpired() {
        return Duration.between(getTimestamp(), LocalDateTime.now()).toHours() >= EXPIRATION_AFTER_SET_HOURS;
    }
    
}
