package connecthub.backend.models.group;

import connecthub.backend.models.Post;

public interface GroupMember {
    String getUsername();

    void post(Post post);

    void leaveGroup(Group group);
}
