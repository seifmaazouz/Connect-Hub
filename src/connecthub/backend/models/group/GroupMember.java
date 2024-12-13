package connecthub.backend.models.group;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;

import java.time.LocalDateTime;


public interface GroupMember {
    String getUsername();

    void post(Post post);

    void leaveGroup(Group group);

    String getRole();

    String getJoinDate();

    User getUser();

    String getUserId();
}
