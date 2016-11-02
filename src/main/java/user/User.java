package user;

import resource.Resource;
import role.Action;
import role.Role;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gakshintala on 4/15/16.
 */
public class User {

    private String mUserId;
    // Assumption: Ignoring other non-significant fields for this context like User Name, User details etc. 

    private Map<Resource, Role> mRoleOnResourceMap = new HashMap<Resource, Role>();

    public User(String userId) {
        this.mUserId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof User)) && this.mUserId.equals(((User) obj).mUserId);
    }

    @Override
    public int hashCode() {
        return this.mUserId.hashCode();
    }

    public void addRoleToResource(Role role, Resource resource) {
        mRoleOnResourceMap.put(resource, role);
    }

    public void unAssignRoleToResource(Resource resource) {
        mRoleOnResourceMap.remove(resource);
    }

    public void modifyRoleToResource(Role role, Resource resource) {
        this.addRoleToResource(role, resource);
    }

    public boolean hasAccess(Action action, Resource resource) {
        Role role = mRoleOnResourceMap.get(resource);
        return role != null && role.hasAccess(action);
    }
}
