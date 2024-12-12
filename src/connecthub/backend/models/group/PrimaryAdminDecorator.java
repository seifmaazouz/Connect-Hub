package connecthub.backend.models.group;

import connecthub.backend.models.User;
import connecthub.backend.services.GroupService;

import java.io.IOException;

public class PrimaryAdminDecorator extends AdminRoleDecorator {
    GroupService groupService;

    public PrimaryAdminDecorator(GroupMember member) {
        super(member);
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
    public Roles getRole() {
        return Roles.PRIMARY_ADMIN;
    }
}
