package connecthub.backend.models.group;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class GroupMemberData {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("role")
    private String role;

    @JsonProperty("joinedAt")
    private Instant joinedAt;

    // Constructors
    public GroupMemberData() {}

    public GroupMemberData(String userId, String role, Instant joinedAt) {
        this.userId = userId;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }
}
