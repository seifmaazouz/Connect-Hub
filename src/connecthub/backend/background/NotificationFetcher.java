package connecthub.backend.background;

import connecthub.backend.models.User;
import connecthub.backend.services.UserService;
import connecthub.frontend.homepage.Homepage;

public class NotificationFetcher implements Runnable {
    private static final long FETCH_INTERVAL = 1000;
    private User user;
    private final Homepage homepage;
    private final UserService userService = UserService.getInstance();
    private boolean running = true;

    public NotificationFetcher(User user, Homepage homepage) {
        this.user = user;
        this.homepage = homepage;
    }

    @Override
    public void run() {
        while(running) {
            fetchNotifications();
            try {
                Thread.sleep(FETCH_INTERVAL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void fetchNotifications() {
        System.out.println("thread running");
        // Fetch notifications for the user
        userService.refreshContents();
        user = userService.getUserById(user.getUserId());
        // update notifications GUI counter
        homepage.updateNotifications(user);
    }

    public void stop() {
        running = false;
    }
}
