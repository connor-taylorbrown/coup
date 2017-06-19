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
     * @param label action label
     * @param coins for current player
     * @param targetCoins effect on target coins
     * @param targetInfluence true if influence reduced
     */
    public TransitiveAction(String label, int coins, int targetCoins, boolean targetInfluence) {
        super(label, coins);
        this.targetCoins = targetCoins;
        this.targetInfluence = targetInfluence;
    }

    public Player getTarget() {
        return target;
    }

    /**
     * Sets target of action before invocation
     * @param target of action
     */
    public void setTarget(Player target) {
        this.target = target;
    }

    /**
     * Apply effect to current player and target. Do not reduce influence below 0.
     */
    @Override
    public void execute() {
        super.execute();
        if(!isBlocked() && !isChallenged()) {
            target.updateCoins(targetCoins);
            if (targetInfluence && target.getInfluence() > 0) {
                target.removeInfluence();
                target.reveal();
            }
        }
    }
}
