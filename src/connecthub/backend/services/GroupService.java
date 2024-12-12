package connecthub.backend.services;

import connecthub.backend.database.GroupDatabase;
import connecthub.backend.models.User;
import connecthub.backend.models.group.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupService {
    private static GroupService instance;
    private GroupDatabase groupDatabase = GroupDatabase.getInstance();
    private List<Group> groups;

    private GroupService() {
        groups = new ArrayList<>();
    }

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    public Group createGroup(String name, String description, Image photo, String creatorId) throws IOException {
        String groupId = UUID.randomUUID().toString();
        Group newGroup = new Group(groupId, name, description, photo);

        UserService userService = UserService.getInstance();
        User creator = userService.getUserById(creatorId);
        GroupMember primaryAdmin = new PrimaryAdminDecorator(new BaseMember(creator, newGroup));
        newGroup.addMember(primaryAdmin);
        groups.add(newGroup);
        groupDatabase.addGroup(newGroup);
        return newGroup;
    }

    public void deleteGroup(Group group) {
        groups.remove(group);
        try {
            groupDatabase.deleteGroup(group.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Group findGroupById(String groupId) {
        return groups.stream()
                .filter(group -> group.getId().equals(groupId))
                .findFirst()
                .orElse(null);
    }

    public List<Group> searchGroups(String query) {
        return groups.stream()
                     .filter(group -> group.getName().toLowerCase().contains(query.toLowerCase()))
                     .toList();
    }

    public void joinGroup(Group group, User user) {
        GroupMember newMember = new BaseMember(user, group);
        group.addMember(newMember);
    }

    public void leaveGroup(Group group, GroupMember member) {
        group.removeMember(member);
    }

    public void promoteToAdmin(Group group, GroupMember member, GroupMember promoter) throws IOException {
        if (promoter instanceof PrimaryAdminDecorator) {
            GroupMember newAdmin = new AdminRoleDecorator(member);
            group.removeMember(member);
            group.addMember(newAdmin);
            groupDatabase.updateGroup(group);
        } else {
            System.out.println("Only the Primary Admin can promote members to Admin");
        }
    }

    public void demoteFromAdmin(Group group, GroupMember member, GroupMember demoter) throws IOException {
        if (demoter instanceof PrimaryAdminDecorator && member instanceof AdminRoleDecorator) {
            UserService userService = UserService.getInstance();
            User user = userService.getUserById(((User)member).getUserId());
            GroupMember demotedMember = new BaseMember(user, group);
            group.removeMember(member);
            group.addMember(demotedMember);
            groupDatabase.updateGroup(group);
        } else {
            System.out.println("Only the Primary Admin can demote Admins");
        }
    }
}
