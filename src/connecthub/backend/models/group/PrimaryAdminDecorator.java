package connecthub.backend.models.group;

import connecthub.backend.models.User;

public class PrimaryAdminDecorator extends AdminRoleDecorator {

    public PrimaryAdminDecorator(GroupMember member) {
        super(member);
    }

    public void deleteGroup(Group group) {
    }

    public void promoteToAdmin(Group group, User member) {
    }

    public void demoteToMember(Group group, User member) {
    }

    public void removeAnyMember(Group group, GroupMember member) {
        group.removeMember(member);
    }
}
