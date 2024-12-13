package connecthub.backend.models.group;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class GroupMemberSerializer extends JsonSerializer<GroupMember> {
    @Override
    public void serialize(GroupMember groupMember, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException, IOException {
        ObjectMapper mapper = (ObjectMapper) generator.getCodec();

        ObjectNode node = mapper.createObjectNode();
        node.put("role", groupMember.getRole());
        node.put("user", mapper.valueToTree(groupMember.getUser()));
        node.put("joinDate", groupMember.getJoinDate().toString());

        switch (groupMember.getRole()) {
            case "PRIMARY_ADMIN":
                generator.writeObject(mapper.convertValue(groupMember, PrimaryAdminDecorator.class));
                break;
            case "ADMIN":
                generator.writeObject(mapper.convertValue(groupMember, AdminRoleDecorator.class));
                break;
            case "MEMBER":
            default:
                generator.writeObject(mapper.convertValue(groupMember, BaseMember.class));
                break;
        }
    }
}
