package connecthub.backend.services;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.database.JSONParser;
import connecthub.backend.models.Friendship;

import java.io.IOException;
import java.util.*;

import static connecthub.backend.constants.FilePath.FRIENDS_FILE_PATH;

public class FriendshipService {
    private HashMap<String, HashMap<String, ArrayList<String>>> friendshipData;
    private final String path = FRIENDS_FILE_PATH;

    public FriendshipService() {
        friendshipData = new HashMap<>();
    }

    public Friendship loadFriendship() throws IOException {
        Friendship friendship = new Friendship();
        JSONParser parser = new JSONParser();
        this.friendshipData = parser.readJSON(this.path, new TypeReference<HashMap<String, HashMap<String, ArrayList<String>>>>() {});

        for (String key : this.friendshipData.keySet()) {
            HashMap<String, ArrayList<String>> value = this.friendshipData.get(key);
            switch (key) {
                case "friends" -> {
                    for (String userId : value.keySet()) {
                        friendship.setFriends(userId, value.get(userId));
                    }
                } case "blocked" -> {
                    for (String userId : value.keySet()) {
                        friendship.setBlocked(userId, value.get(userId));
                    }
                } case "blockedBy" -> {
                    for (String userId : value.keySet()) {
                        friendship.setBlockedBy(userId, value.get(userId));
                    }
                } case "receivedRequests" -> {
                    for (String userId : value.keySet()) {
                        friendship.setReceivedRequests(userId, value.get(userId));
                    }
                } case "sentRequests" -> {
                    for (String userId : value.keySet()) {
                        friendship.setSentRequests(userId, value.get(userId));
                    }
                }
            }
            }
        return friendship;
    }

    public void saveFriendship(Friendship friendship) {
        JSONParser parser = new JSONParser();
        HashMap<String, HashMap<String, ArrayList<String>>> friendshipData = new HashMap<>();
        friendshipData.put("friends", friendship.getFriends());
        friendshipData.put("blocked", friendship.getBlocked());
        friendshipData.put("blockedBy", friendship.getBlockedBy());
        friendshipData.put("receivedRequests", friendship.getFriendRequests());
        friendshipData.put("sentRequests", friendship.getSentRequests());
        try {
            parser.writeJSON(this.path, friendshipData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
