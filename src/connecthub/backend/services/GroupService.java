package connecthub.backend.services;

import connecthub.backend.database.GroupDatabase;
import connecthub.backend.models.ContentData;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.models.group.*;
import connecthub.backend.utils.factories.ContentFactory;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupService {
    private static GroupService instance;
    private GroupDatabase groupDatabase = GroupDatabase.getInstance();
    private List<Group> groups;


    public GroupService() {
        groups = new ArrayList<>();
    }

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    public Group createGroup(String name, String description, String photoUrl, String creatorId) throws IOException {
        String groupId = UUID.randomUUID().toString();
        Group newGroup = new Group(groupId, name, description, photoUrl);

        UserService userService = UserService.getInstance();
        User creator = userService.getUserById(creatorId);
        GroupMember primaryAdmin = new PrimaryAdminDecorator(creator, newGroup, LocalDateTime.now().toString());
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

    public void joinGroup(Group group, User user) throws IOException {
        GroupMember newMember = new BaseMember(user, group, LocalDateTime.now().toString());
        group.addMember(newMember);
        groupDatabase.updateGroup(group);
    }

    public void leaveGroup(Group group, GroupMember member) throws IOException {
        group.removeMember(member);
        groupDatabase.updateGroup(group);
    }

    public boolean promoteToAdmin(Group group, GroupMember member, GroupMember promoter) throws IOException {
        if (promoter instanceof PrimaryAdminDecorator) {
            GroupMember newAdmin = new AdminRoleDecorator((User) member, group, promoter.getJoinDate());
            group.removeMember(member);
            group.addMember(newAdmin);
            groupDatabase.updateGroup(group);
            return true;
        }
        return false;
    }

    public boolean demoteFromAdmin(Group group, GroupMember member, GroupMember demoter) throws IOException {
        if (demoter instanceof PrimaryAdminDecorator && member instanceof AdminRoleDecorator) {
            UserService userService = UserService.getInstance();
            User user = userService.getUserById(((User)member).getUserId());
            GroupMember demotedMember = new BaseMember(user, group, member.getJoinDate());
            group.removeMember(member);
            group.addMember(demotedMember);
            groupDatabase.updateGroup(group);
            return true;
        }

        return false;
    }

    public void addPost(String groupId, ContentData content, String authorId) throws IOException {
        Group group = findGroupById(groupId);
        if (group != null) {
            Post newPost = ContentFactory.createPost(authorId, content.getText(), content.getImagePath());
            group.addPost(newPost);
            groupDatabase.updateGroup(group);
        }
    }

    public void removePost(String groupId, String postId, String removerId) throws IOException {
        Group group = findGroupById(groupId);
        if (group != null) {
            boolean isAdmin = group.getMembers().stream()
                    .anyMatch(member -> ((User)member).getUserId().equals(removerId) &&
                            (member.getRole() == "ADMIN" || member.getRole() == "PRIMARY_ADMIN"));
            if (isAdmin) {
                group.setPosts(group.getPosts().stream()
                        .filter(post -> !post.getContentId().equals(postId))
                        .toList());
                groupDatabase.updateGroup(group);
            }
        }
    }
}
