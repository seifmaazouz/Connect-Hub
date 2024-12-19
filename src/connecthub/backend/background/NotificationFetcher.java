package connecthub.backend.background;

import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
import connecthub.frontend.homepage.Homepage;

public class NotificationFetcher implements Runnable {
    private static final long FETCH_INTERVAL = 1000;
    private final User user;
    private final Homepage homepage;
    private final UserService userService = UserService.getInstance();

    public NotificationFetcher(User user, Homepage homepage) {
        this.user = user;
        this.homepage = homepage;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            fetchNotifications();
            // update notifications GUI counter
            homepage.updateNotifications(user.getNotifications());
            try {
                Thread.sleep(FETCH_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void fetchNotifications() {
        // Fetch notifications for the user
        userService.updateUser(user.getUserId(), user);
    }
}
