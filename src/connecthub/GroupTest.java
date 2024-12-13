package connecthub;

import connecthub.backend.models.ContentData;
import connecthub.backend.models.User;
import connecthub.backend.models.group.BaseMember;
import connecthub.backend.models.group.Group;
import connecthub.backend.models.group.GroupMember;
import connecthub.backend.models.group.PrimaryAdminDecorator;
import connecthub.backend.services.GroupService;
import connecthub.backend.services.UserService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class GroupTest {
     public static void main(String[] args) {
         try {
             UserService userManager = UserService.getInstance();
             GroupService groupManager = GroupService.getInstance();



             // Create a group (working)
             String photoUrl = "/home/ahmednader/Desktop/University/Year 3/semester_05/programming_2/assignments/Connect-Hub/src/connecthub/backend/database/images/0_2024-12-06_15-14-14.jpg";
             Group techGroup = groupManager.createGroup("Tech Enthusiasts", "A group for tech lovers", photoUrl, "1019");
             System.out.println("Group created: " + techGroup.getName());

             GroupMember user1 = new BaseMember(userManager.getUserById("1019"), techGroup, LocalDateTime.now().toString());
             // Join group (working)
             System.out.println("Group members: " + techGroup.getMembers().size());

             GroupMember user2 = new BaseMember(userManager.getUserById("1018"), techGroup, LocalDateTime.now().toString());
             groupManager.joinGroup(techGroup, (User) user1);
             groupManager.joinGroup(techGroup, (User) user2);
             System.out.println("Group members: " + techGroup.getMembers().size());

             // Add posts
             groupManager.addPost(techGroup.getId(),new ContentData("Welcome to the Tech Enthusiasts group!"), "1019");
             groupManager.addPost(techGroup.getId(),new ContentData( "Hello everyone, excited to be here!"), "1018");
             System.out.println("Group posts: " + techGroup.getPosts().size());

             // Promote user to admin
             groupManager.promoteToAdmin(techGroup, user2, user1);
             System.out.println("User promoted to admin");
             // Search for groups
             List<Group> searchResults = groupManager.searchGroups("Tech");
             for (Group group : searchResults) {
                 System.out.println("Found group: " + group.getName());
             }


             // Get updated group data
             Group updatedGroup = groupManager.findGroupById(techGroup.getId());
             System.out.println("Group members: " + updatedGroup.getMembers().size());
             System.out.println("Group posts: " + updatedGroup.getPosts().size());

             // Remove a post
             String postIdToRemove = updatedGroup.getPosts().get(0).getContentId();
             groupManager.removePost(techGroup.getId(), postIdToRemove, "1019");

             // Leave group
             groupManager.leaveGroup(techGroup, user2);

             // Delete group
             groupManager.deleteGroup(techGroup);
             System.out.println("Group deleted");

         } catch (IOException e) {
             e.printStackTrace();
         }
     }

}
