package connecthub.backend.models;

import java.util.List;

public interface Interactable {
    public void comment(String userId, String comment);
    public void like(String userId);
    public int getLikes();
    public List<Comment> getComments();
}
