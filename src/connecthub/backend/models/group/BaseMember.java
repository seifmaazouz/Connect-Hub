package connecthub.backend.models.group;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;

public class BaseMember extends User implements GroupMember {
    protected Group group;
    protected User user;

    public BaseMember(User user, Group group) {
        this.user = user;
        this.group = group;
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
    public Roles getRole() {
        return Roles.MEMBER;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
