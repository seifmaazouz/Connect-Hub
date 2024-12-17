package connecthub.backend.profile;

import connecthub.backend.models.Friendship;
import connecthub.backend.models.User;
import connecthub.backend.services.FriendshipService;
import java.io.IOException;
import java.util.List;

public class FetchFriends {

    private User user;

    public FetchFriends(User user) {
        this.user = user;
    }

    public List<String> fetch() throws IOException {
        FriendshipService friendshipService = new FriendshipService();
        Friendship friendship = friendshipService.loadFriendship();
        List<String> friends = friendship.getUserFriends(user.getUserId());
        return friends;
    }
}
