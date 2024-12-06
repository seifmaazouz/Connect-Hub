package connecthub.backend.profile;

import java.util.List;

public class FetchFriends {

    private String userId;

    public FetchFriends(String userId) {
        this.userId = userId;
    }

//    public List<Friend> fetch() {
//        FriendService friend = ServiceFactory.createFriendService();
//        List<Friend> friends = friend.getListOfUserFriends(userId);
//        return friends;
//    }
}
