package connecthub.backend.models.group;

import connecthub.backend.models.User;
import connecthub.backend.services.GroupService;

import java.io.IOException;
import java.time.LocalDateTime;

public class PrimaryAdminDecorator extends AdminRoleDecorator {
    GroupService groupService;

    public PrimaryAdminDecorator(User user, Group group, String joinDate) {
        super(user, group, joinDate);
        this.groupService = GroupService.getInstance();
    }

    public void deleteGroup(Group group) {
        groupService.deleteGroup(group);
    }

    public void promoteToAdmin(Group group, GroupMember member) throws IOException {
        groupService.promoteToAdmin(group, member, PrimaryAdminDecorator.this);
    }

    public void demoteToMember(Group group, GroupMember member) throws IOException {
        groupService.demoteFromAdmin(group, member, PrimaryAdminDecorator.this);
    }

    public void removeAnyMember(Group group, GroupMember member) {
        group.removeMember(member);
    }

    @Override
    public String getRole() {
        return "PRIMARY_ADMIN";
    }
}
