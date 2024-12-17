package connecthub.backend.models;

public interface Interactable {
    public void comment(String userId, String comment);
    public void like(String userId);
}
