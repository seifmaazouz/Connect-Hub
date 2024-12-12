package connecthub.backend.models.group;

import com.fasterxml.jackson.annotation.JsonProperty;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("photoUrl")
    private Image groupPhoto;

    @JsonProperty("primaryAdminId")
    private String primaryAdminId;

    @JsonProperty("members")
    private List<GroupMember> members;

    @JsonProperty("posts")
    private List<Post> posts;

    public Group(String id, String name, String description, Image groupPhoto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupPhoto = groupPhoto;
        this.members = new ArrayList<>();
        this.posts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getGroupPhoto() {
        return groupPhoto;
    }

    public void setGroupPhoto(Image groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    public ArrayList<GroupMember> getMembers() {
        return new ArrayList<>(members);
    }

    public void addMember(GroupMember member) {
        members.add(member);
    }

    public void removeMember(GroupMember member) {
        members.remove(member);
    }

    public ArrayList<Post> getPosts() {
        return new ArrayList<>(posts);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrimaryAdminId(String primaryAdminId) {
        this.primaryAdminId = primaryAdminId;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

}
