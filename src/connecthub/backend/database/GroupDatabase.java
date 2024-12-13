package connecthub.backend.database;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import connecthub.backend.models.ContentData;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;
import connecthub.backend.models.group.*;
import connecthub.backend.services.UserService;
import connecthub.backend.utils.ImageLoader;
import com.fasterxml.jackson.datatype.jsr310.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static connecthub.backend.constants.FilePath.GROUPS_FILE_PATH;

public class GroupDatabase {
    private UserService userService = UserService.getInstance();
    private static GroupDatabase instance;
    private final JSONParser jsonParser;

    private GroupDatabase() {
        this.jsonParser = new JSONParser();
    }

    public static synchronized GroupDatabase getInstance() {
        if (instance == null) {
            instance = new GroupDatabase();
        }
        return instance;
    }

    public synchronized List<Group> loadGroups() throws IOException {
        File file = new File(GROUPS_FILE_PATH);
        if (file.exists() && file.length() > 0) {
            return read();
        }
        return new ArrayList<>();
    }

    public synchronized void saveGroups(List<Group> groups) throws IOException {
        write(groups);
    }

    public synchronized void addGroup(Group group) throws IOException {
        List<Group> groups = loadGroups();
        if (!groups.contains(group)) { // Ensure no duplicate groups
            groups.add(group);
            saveGroups(groups);
        }
    }

    public synchronized void updateGroup(Group updatedGroup) throws IOException {
        List<Group> groups = loadGroups();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equals(updatedGroup.getId())) {
                groups.set(i, updatedGroup);
                saveGroups(groups);
                return;
            }
        }
        throw new IllegalArgumentException("Group with ID " + updatedGroup.getId() + " not found.");
    }

    public synchronized void deleteGroup(String groupId) throws IOException {
        List<Group> groups = loadGroups();
        if (groups.removeIf(group -> group.getId().equals(groupId))) {
            saveGroups(groups);
        } else {
            throw new IllegalArgumentException("Group with ID " + groupId + " not found.");
        }
    }

    public synchronized Group getGroupById(String groupId) throws IOException {
        return loadGroups().stream()
                .filter(group -> group.getId().equals(groupId))
                .findFirst()
                .orElse(null);
    }



    private LocalDateTime dateParser(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        return LocalDateTime.parse(date, formatter);
    }



    public List<Group> read() {
//        ArrayList<Group> groups = new ArrayList<>();
//        ArrayList<Map<String, Object>> groupList = (ArrayList<Map<String, Object>>) data.get("groups");
//
//
//        for (Map<String, Object> groupData : groupList) {
//
//            String id = ((String) groupData.get("id"));
//            String name = ((String) groupData.get("name"));
//            String description = ((String) groupData.get("description"));
//            Image image = ImageLoader.loadImage((String) groupData.get("photoUrl"));
//
//            Group group = new Group(id, name, description, image);
//
//            ArrayList<Map<String, Object>> membersData = (ArrayList<Map<String, Object>>) groupData.get("members");
//            ArrayList<GroupMember> members = new ArrayList<>();
//            for (Map<String, Object> memberData : membersData) {
//                String joinDate = (String) memberData.get("joinedAt");
//                GroupMember member =
//                        switch ((String) memberData.get("role")) {
//                            case "PRIMARY_ADMIN" -> new PrimaryAdminDecorator(userService.getUserById((String) memberData.get("userId")), group, joinDate);
//                            case "ADMIN" -> new AdminRoleDecorator(userService.getUserById((String) memberData.get("userId")), group, joinDate);
//                            case "MEMBER" -> new BaseMember(userService.getUserById((String) memberData.get("userId")), group, joinDate);
//                            default -> throw new IllegalStateException("Unexpected value: " + memberData.get("role"));
//                        };
//                members.add(member);
//            }
//            group.setMembers(members);
//
//            ArrayList<Map<String, Object>> postsData = (ArrayList<Map<String, Object>>) groupData.get("posts");
//            ArrayList<Post> posts = new ArrayList<>();
//            for (Map<String, Object> postData : postsData) {
//                Post post = new Post(
//                        (String) postData.get("id"),
//                        (String) postData.get("authorId"),
//                        new ContentData((String) postData.get("content")),
//                        dateParser((postData.get("createdAt").toString()))
//                );
//                posts.add(post);
//            }
//            group.setPosts(posts);
//            groups.add(group);
//        }
//
//        return groups;

        ObjectMapper mapper = new ObjectMapper();

        try {
            Group[] groups = mapper.readValue(new FileReader(GROUPS_FILE_PATH), Group[].class);
            return List.of(groups);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    public void write(List<Group> groups) throws IOException {
////        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.registerModule(new JavaTimeModule()); // Register the JSR310 module for LocalDateTime
////        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print JSON
//
//        // Convert list of groups into the required format
////        Map<String, Object> result = new HashMap<>();
////        List<Map<String, Object>> groupJsonList = new ArrayList<>();
////        for (Group group : groups) {
////            Map<String, Object> groupJson = new HashMap<>();
////            groupJson.put("id", group.getId());
////            groupJson.put("name", group.getName());
////            groupJson.put("description", group.getDescription());
////            groupJson.put("photoUrl", getImagePath(group.getGroupPhoto()));
////
////            // Convert members list
////            List<Map<String, Object>> membersJsonList = new ArrayList<>();
////            for (GroupMember member : group.getMembers()) {
////                Map<String, Object> memberJson = new HashMap<>();
////
////                memberJson.put("userId",((User) member).getUserId());
////                memberJson.put("role", member.getRole());
////                memberJson.put("joinedAt", member.getJoinDate());
////                membersJsonList.add(memberJson);
////            }
////            groupJson.put("members", membersJsonList);
////
////            // Convert posts list
////            List<Map<String, Object>> postsJsonList = new ArrayList<>();
////            for (Post post : group.getPosts()) {
////                Map<String, Object> postJson = new HashMap<>();
////                postJson.put("id", post.getContentId());
////                postJson.put("content", post.getContentData().getText());
////                postJson.put("authorId", post.getAuthorId());
////                postJson.put("createdAt", post.getTimestamp());
////                postsJsonList.add(postJson);
////            }
////            groupJson.put("posts", postsJsonList);
////
////            groupJsonList.add(groupJson);
////        }
////
////        result.put("groups", groupJsonList);
////
////        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new JavaTimeModule()); // Register the JSR310 module for LocalDateTime
//        mapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty print JSON
//
//        Group[] groups = mapper.readValue(new FileReader(GROUPS_FILE_PATH), new Group[].class);
//
//
//        return "";
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        mapper.writeValue(new File(GROUPS_FILE_PATH), groups);

    }

    public static String getImagePath(Image image) {
        if (image instanceof BufferedImage) {
            BufferedImage bufferedImage = (BufferedImage) image;
            File file = new File(String.valueOf(bufferedImage.getProperty("fileName")));
            return file.getAbsolutePath();
        }
        return null;
    }
}
