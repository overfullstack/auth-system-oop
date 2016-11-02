import resource.Resource;
import role.Role;
import role.Action;
import user.User;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by gakshintala on 4/15/16.
 */
public class SystemAPI {
    private static Map<String, User> mUsers = new HashMap<>();

    // Note: Passing userId String instead of User object, coz client doesn't need to get exposed or depend on User impl
    public static void addUser(String userId) {
        mUsers.put(userId, new User(userId));
    }

    public static void batchAddUsers(String[] userIds) {
        for (String userId : userIds) {
            mUsers.put(userId, new User(userId));
        }
    }

    private static User checkAndGetUser(String userId) {
        User user = mUsers.get(userId);
        if (user == null) {
            throw new NoSuchElementException("UserId: " + userId + " not found in the System");
        }
        return user;
    }

    // A role is always given on a 
    public static void assignRoleOnResourceToUser(String userId, Role role, Resource resource) {
        // Without exposing User's internals, delegate this task to user itself.
        checkAndGetUser(userId).addRoleToResource(role, resource);
    }

    public static void unAssignRoleOnResourceToUser(String userId, Resource resource) {
        checkAndGetUser(userId).unAssignRoleToResource(resource);
    }

    public static boolean hasAccessOnResourceToUser(String userId, Action action, Resource resource) {
        return checkAndGetUser(userId).hasAccess(action, resource);
    }
}
