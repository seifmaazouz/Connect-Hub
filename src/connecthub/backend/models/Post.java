package connecthub.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Post extends Content implements Interactable {
    private final List<Comment> comments;
    private final Set<String> likedBy;

    // Default constructor (required for Jackson deserialization)
    public Post() {
        comments = new ArrayList<>();
        likedBy = new HashSet<>();
    }

    public Post(String contentId, String authorId, ContentData contentData) {
        super(contentId, authorId, contentData);
        comments = new ArrayList<>();
        likedBy = new HashSet<>();
    }

    @Override
    public void comment(String username, String comment) {

        comments.add(new Comment(username, comment));
    }

    @Override
    public void like(String userId) {
        if (!likedBy.contains(userId)) { // like
            likedBy.add(userId);
        } else { // dislike
            likedBy.remove(userId);
        }
    }

    @Override
    public List<Comment> getComments() {
        return comments;
    }

    public Set<String> getLikedBy() {
        return likedBy;
    }

    @JsonIgnore
    @Override
    public int getLikes() {
        return likedBy.size();
    }
}
