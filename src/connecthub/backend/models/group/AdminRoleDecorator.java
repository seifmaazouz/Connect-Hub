package connecthub.backend.models.group;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.services.GroupService;

import java.io.IOException;
import java.time.LocalDateTime;

public class AdminRoleDecorator extends BaseMember {
    GroupService groupService = GroupService.getInstance();
    public AdminRoleDecorator(User user, Group group, String joinDate) {
        super(user, group, joinDate);
    }

    public void editPost(Post post, Post newPost) {
        post.setContentData(newPost.getContentData());
    }


    public void deletePost(Post post, Group group) {
        group.deletePost(post);
    }

    public boolean removeMember(Group group, GroupMember member) {
        if (member instanceof BaseMember) {
            group.removeMember(member);
            return true;
        }
        return false;
    }

    public void approveMembershipRequest(GroupMember member, Group group) throws IOException {
        groupService.joinGroup(group, (User) member);
    }

    public void declineMembershipRequest(GroupMember member, Group group) {

    }

    @Override
    public String getRole() {
        return "ADMIN";
    }
}
