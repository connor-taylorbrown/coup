package coup;

/**
 * Provides an interface for Invokers to interact with Receivers according to the Command Pattern. The current player
 * is Invoker and Receiver. The current player is changed throughout the lifetime of the Action object, a modification
 * to support the definition of actions at runtime.
 */
public abstract class Action {
    protected Player player;
    private boolean blocked;
    private boolean challenged;

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

    public abstract void execute();
}
