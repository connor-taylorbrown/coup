package coup;

/**
 * Performs an action affecting the current player and a target.
 */
public class TransitiveAction extends IntransitiveAction {
    private int targetCoins;
    private boolean targetInfluence;
    private Player target;

    /**
     * Initialise effect of action on current player coins and target coins and influence
     * @param coins for current player
     * @param targetCoins effect on target coins
     * @param targetInfluence true if influence reduced
     */
    public TransitiveAction(int coins, int targetCoins, boolean targetInfluence) {
        super(coins);
        this.targetCoins = targetCoins;
        this.targetInfluence = targetInfluence;
    }

    /**
     * Sets target of action before invocation
     * @param target of action
     */
    public void setTarget(Player target) {
        this.target = target;
    }

    /**
     * Apply effect to current player and target
     */
    @Override
    public void execute() {
        if(!isBlocked() && !isChallenged()) {
            super.execute();
            target.updateCoins(targetCoins);
            if (targetInfluence) target.removeInfluence();
        }
    }
}
