package coup;

/**
 * Provides an interface for Invokers to interact with Receivers according to the Command Pattern. The current player
 * is Invoker and Receiver. The current player is changed throughout the lifetime of the Action object, a modification
 * to support the definition of actions at runtime.
 */
public abstract class Action {
    protected Player player;
    private boolean blocked = false;
    private boolean challenged = false;

    public void setPlayer(Player player) {
        this.player = player;
    }

    protected boolean isBlocked() {
        return blocked;
    }

    protected void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    protected boolean isChallenged() {
        return challenged;
    }

    protected void setChallenged(boolean challenged) {
        this.challenged = challenged;
    }

    /**
     * Queries whether a player is allowed to perform this action
     * @param player seeking to perform action
     * @param trigger action player is responding to
     * @return true if permitted by rules
     */
    public abstract boolean canPerform(Player player, Action trigger);

    public abstract void execute();
}
