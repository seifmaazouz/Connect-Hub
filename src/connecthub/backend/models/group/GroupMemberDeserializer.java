package connecthub.backend.models.group;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class GroupMemberDeserializer extends JsonDeserializer<GroupMember> {
    @Override
    public GroupMember deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) parser.getCodec();
        ObjectNode node = mapper.readTree(parser);

        // Identify the role or any distinguishing property
        String role = node.get("role").asText();

        switch (role) {
            case "PRIMARY_ADMIN":
                return mapper.treeToValue(node, PrimaryAdminDecorator.class);
            case "ADMIN":
                return mapper.treeToValue(node, AdminRoleDecorator.class);
            case "MEMBER":
            default:
                return mapper.treeToValue(node, BaseMember.class);
        }

    }
}
