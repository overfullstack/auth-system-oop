/*
 * Copyright (c) 2016, Gopal S Akshintala. 
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 * http://creativecommons.org/licenses/by-sa/4.0/
 */

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import resource.Resource;
import role.Action;
import role.Role;

import java.util.NoSuchElementException;

/**
 * Created by gakshintala on 4/15/16.
 */
public class SystemAPITest {
    private Resource[] mResources;

    @Before
    public void setUp() {
        addUsersToSystem();
        prepareDummyResources();
    }

    private void addUsersToSystem() {
        for (int i = 0; i < 5; i++) {
            SystemAPI.addUser(String.valueOf(i));
        }
    }

    private void prepareDummyResources() {
        int i = 0;
        mResources = new Resource[5];
        for (Resource resource : mResources) {
            resource = new Resource(String.valueOf(i++)) {
                @Override
                public void readFromResource() {
                }

                @Override
                public void writeToResource() {
                }

                @Override
                public void deleteFromResource() {
                }
            };
        }
    }

    @Test
    public void testResourceAccessAfterAssignRole() {
        SystemAPI.assignRoleOnResourceToUser("0", Role.ADMIN, mResources[0]);
        Assert.assertTrue(SystemAPI.hasAccessOnResourceToUser("0", Action.DELETE, mResources[0]));
    }

    @Test
    public void testResourceAccessAfterUnassignRole() {
        SystemAPI.unAssignRoleOnResourceToUser("0", mResources[0]);
        Assert.assertFalse(SystemAPI.hasAccessOnResourceToUser("0", Action.READ, mResources[0]));
    }
    
    @Test
    public void testForUnassignedResource() {
        SystemAPI.assignRoleOnResourceToUser("0", Role.GUEST, mResources[0]);
        Assert.assertFalse(SystemAPI.hasAccessOnResourceToUser("0", Action.DELETE, mResources[1]));
    }

    @Test(expected = NoSuchElementException.class)
    public void testUserIdNotFound() {
        SystemAPI.hasAccessOnResourceToUser("5", Action.DELETE, mResources[0]);
    }

    @After
    public void tearDown() {
        mResources = null;
    }
}
