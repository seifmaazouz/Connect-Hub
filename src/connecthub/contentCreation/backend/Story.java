package connecthub.contentCreation.backend;

import java.time.Duration;
import java.time.LocalTime;

public class Story extends Content implements Expirable {
    private static final long EXPIRATION_AFTER_SET_HOURS = 24;

    public Story(String authorId, ContentData content) {
        super(authorId, content);
    }

    @Override
    public boolean isExpired() {
        return Duration.between(getTimestamp(), LocalTime.now()).toHours() >= EXPIRATION_AFTER_SET_HOURS;
    }
    
}
