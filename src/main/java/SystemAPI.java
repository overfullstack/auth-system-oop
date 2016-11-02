/*
 * Copyright (c) 2016, Gopal S Akshintala. 
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 * http://creativecommons.org/licenses/by-sa/4.0/
 */

import resource.Resource;
import role.Action;
import role.Role;
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

    // A role is always given on a
    public static void assignRoleOnResourceToUser(String userId, Role role, Resource resource) {
        // Without exposing User's internals, delegate this task to user itself.
        checkAndGetUser(userId).addRoleToResource(role, resource);
    }

    private static User checkAndGetUser(String userId) {
        User user = mUsers.get(userId);
        if (user == null) {
            throw new NoSuchElementException("UserId: " + userId + " not found in the System");
        }
        return user;
    }

    public static void unAssignRoleOnResourceToUser(String userId, Resource resource) {
        checkAndGetUser(userId).unAssignRoleToResource(resource);
    }

    public static boolean hasAccessOnResourceToUser(String userId, Action action, Resource resource) {
        return checkAndGetUser(userId).hasAccess(action, resource);
    }
}
