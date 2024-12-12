package connecthub.backend.models.group;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;


public abstract class GroupMemberDecorator implements GroupMember {
    protected GroupMember member;

    public GroupMemberDecorator(GroupMember member) {
        this.member = member;
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public void post(Post post) {
        member.post(post);
    }

    @Override
    public void leaveGroup(Group group) {
        member.leaveGroup(group);
    }

}

