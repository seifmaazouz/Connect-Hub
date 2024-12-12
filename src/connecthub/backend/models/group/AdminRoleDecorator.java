package connecthub.backend.models.group;

import connecthub.backend.models.Post;
import connecthub.backend.models.User;

public class AdminRoleDecorator extends GroupMemberDecorator {

    public AdminRoleDecorator(GroupMember member) {
        super(member);
    }

    public void editPost(String content) {
//        member.editPost(content);
    }

    public void deletePost(Post post, Group group) {
        group.deletePost(post);
    }

    public void removeMember(Group group, GroupMember member) {
        if (member instanceof GroupMemberDecorator) {
            group.removeMember(member);
        } else {
            System.out.println("Can't remove an admin from the group");
        }
        group.removeMember(member);
    }

    public void approveMembershipRequest(String userName) {
//        System.out.println(member.getUsername() + " approved membership request for: " + userName);
    }

    public void declineMembershipRequest(String userName) {
//        System.out.println(member.getUsername() + " declined membership request for: " + userName);
    }
}
