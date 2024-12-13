package connecthub.backend.models.group;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;

import java.time.LocalDateTime;

public class BaseMember extends User implements GroupMember {
    protected Group group;
    protected User user;
    protected String joinDate;


    @Override
    public User getUser() {
        return user;
    }


    public BaseMember(User user, Group group, String joinDate) {
        this.user = user;
        this.group = group;
        this.joinDate = joinDate;
    }

    @Override
    public void post(Post post) {
        group.addPost(post);
    }

    @Override
    public void leaveGroup(Group group) {
        group.removeMember(BaseMember.this);
    }

    @Override
    public String getRole() {
        return "MEMBER";
    }

    @Override
    public String getJoinDate() {
        return joinDate;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public String getUserId() {
        return user.getUserId();
    }
}
