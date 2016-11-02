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
