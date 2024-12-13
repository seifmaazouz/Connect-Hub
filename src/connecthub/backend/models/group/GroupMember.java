package connecthub.backend.models.group;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import connecthub.backend.models.Post;
import connecthub.backend.models.User;

import java.time.LocalDateTime;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BaseMember.class, name = "BaseMember"),
        @JsonSubTypes.Type(value = PrimaryAdminDecorator.class, name = "PrimaryAdminDecorator"),
        @JsonSubTypes.Type(value = AdminRoleDecorator.class, name = "AdminRoleDecorator")
})
public interface GroupMember {
    String getUsername();

    void post(Post post);

    void leaveGroup(Group group);

    String getRole();

    String getJoinDate();

    User getUser();

    String getUserId();
}
