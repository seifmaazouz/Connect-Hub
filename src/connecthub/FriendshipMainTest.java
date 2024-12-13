package connecthub;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.database.JSONParser;
import connecthub.backend.models.Friendship;
import connecthub.backend.services.FriendshipService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static connecthub.backend.constants.FilePath.FRIENDS_FILE_PATH;

public class FriendshipMainTest {
    public static void main(String[] args) throws IOException {
        JSONParser jsonParser = new JSONParser();

        HashMap<String, HashMap<String, ArrayList<String>>> friendshipData = jsonParser
                .readJSON(FRIENDS_FILE_PATH,
                        new TypeReference<HashMap<String, HashMap<String, ArrayList<String>>>>() {});

        FriendshipService friendshipService = new FriendshipService();
        Friendship friendship = friendshipService.loadFriendship();
        System.out.println(friendship.toString());

        friendship.makeFriends("1", "1023");
        friendship.makeFriends("1", "1022");
        friendship.makeFriends("1023", "1022");

        friendshipService.saveFriendship(friendship);
    }
}
