package coup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Treats a turn as a single Action by means of the Composite Pattern. Actions are added to a stack and executed from
 * the top of the stack.
 */
public class Turn extends Action {
    private List<Action> actions;

    /**
     * Initialise a new turn stack
     */
    public Turn() {
        this.actions = new ArrayList<>();
    }

    /**
     * Add a new action to the turn stack
     * @param action to add to stack
     */
    public void add(Action action) {
        actions.add(action);
    }

    /**
     * Pop and execute each action in the turn stack
     */
    @Override
    public void execute() {
        Collections.reverse(actions);   // Java was a stack really so hard???!!1!11!!
        for(Action action: actions) {
            action.execute();
        }
    }
}
