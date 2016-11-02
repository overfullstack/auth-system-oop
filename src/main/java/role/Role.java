/*
 * Copyright (c) 2016, Gopal S Akshintala. 
 * This source code is licensed under the Creative Commons Attribution-ShareAlike 4.0 International License.
 * http://creativecommons.org/licenses/by-sa/4.0/
 */

package role;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by gakshintala on 4/15/16.
 */
public enum Role {
    ADMIN(EnumSet.of(Action.READ, Action.WRITE, Action.DELETE)),
    OPERATOR(EnumSet.of(Action.READ, Action.WRITE)),
    GUEST(EnumSet.of(Action.READ));

    Set<Action> actions;

    Role(EnumSet<Action> actions) {
        this.actions = actions;
    }

    public boolean hasAccess(Action action) {
        return actions.contains(action);
    }
}
