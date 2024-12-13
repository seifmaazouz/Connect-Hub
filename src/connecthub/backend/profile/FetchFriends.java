package connecthub.backend.profile;

import connecthub.backend.models.User;
import java.util.List;

public class FetchFriends {

    private User user;

    public FetchFriends(User user) {
        this.user = user;
    }

//    public List<User> fetch() {
//        List<User> friends = user.getFriendship().getFriends();
//        return friends;
//    }
}
