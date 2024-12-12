package connecthub.backend.database;

import com.fasterxml.jackson.core.type.TypeReference;
import connecthub.backend.models.group.Group;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static connecthub.backend.constants.FilePath.GROUPS_FILE_PATH;

public class GroupDatabase {
    private static JSONParser jsonParser;
    private static GroupDatabase instance;

    private GroupDatabase() {
        jsonParser = new JSONParser();
    }

    public static GroupDatabase getInstance() {
        if (instance == null) {
            instance = new GroupDatabase();
        }
        return instance;
    }
    public static List<Group> loadGroups() throws IOException {
        jsonParser = new JSONParser();
        File file = new File(GROUPS_FILE_PATH);
        if (file.exists()) {
            return jsonParser.readJSON(GROUPS_FILE_PATH, new TypeReference<List<Group>>() {});
        } else {
            return new ArrayList<>();
        }
    }

    public static void saveGroups(List<Group> groups) throws IOException {
        jsonParser.writeJSON(GROUPS_FILE_PATH, groups);
    }

    public static void addGroup(Group group) throws IOException {
        List<Group> groups = loadGroups();
        groups.add(group);
        saveGroups(groups);
    }

    public static void updateGroup(Group updatedGroup) throws IOException {
        List<Group> groups = loadGroups();
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getId().equals(updatedGroup.getId())) {
                groups.set(i, updatedGroup);
                break;
            }
        }
        saveGroups(groups);
    }

    public static void deleteGroup(String groupId) throws IOException {
        List<Group> groups = loadGroups();
        groups.removeIf(group -> group.getId().equals(groupId));
        saveGroups(groups);
    }

    public static Group getGroupById(String groupId) throws IOException {
        List<Group> groups = loadGroups();
        return groups.stream()
                .filter(group -> group.getId().equals(groupId))
                .findFirst()
                .orElse(null);
    }
}
