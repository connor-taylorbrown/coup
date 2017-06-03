package coup;

/**
 * Provides an interface for Invokers to interact with Receivers according to the Command Pattern. The current player
 * is Invoker and Receiver. The current player is changed throughout the lifetime of the Action object, a modification
 * to support the definition of actions at runtime.
 */
public abstract class Action {
    Player player;

    /**
     * Sets receiver to current player before invocation.
     * @param player current player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void execute();
}
