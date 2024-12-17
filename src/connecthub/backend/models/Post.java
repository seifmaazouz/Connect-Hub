package connecthub.backend.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class Post extends Content implements Interactable {
    private HashMap<String, String> comments;
    private Set<String> likedBy;

    // Default constructor (required for Jackson deserialization)
    public Post() {
        comments = new LinkedHashMap<>();
        likedBy = new HashSet<>();
    }

    public Post(String contentId, String authorId, ContentData contentData) {
        super(contentId, authorId, contentData);
        comments = new LinkedHashMap<>();
        likedBy = new HashSet<>();
    }

    @Override
    public void comment(String userId, String comment) {
        comments.put(userId, comment);
    }

    @Override
    public void like(String userId) {
        if (!likedBy.contains(userId)) { // like
            likedBy.add(userId);
        } else { // dislike
            likedBy.remove(userId);
        }
    }

    public HashMap<String, String> getComments() {
        return comments;
    }

    public Set<String> getLikedBy() {
        return likedBy;
    }

    @JsonIgnore
    public int getLikes() {
        return likedBy.size();
    }
}
