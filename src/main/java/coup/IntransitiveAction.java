package coup;

/**
 * Performs action affecting only the current player.
 */
public class IntransitiveAction extends Action {
    private int coins;

    /**
     * Initialise effect of action on current player coins
     * @param label action label
     * @param coins for current player
     */
    public IntransitiveAction(String label, int coins) {
        this.label = label;
        this.coins = coins;
    }

    @Override
    public boolean canPerform(Player player, Action trigger) {
        return player.getCoins() + coins >= 0;
    }

    /**
     * Apply effect to current player if it carries cost or is not blocked or challenged
     */
    @Override
    public void execute() {
        if(coins < 0 || !isBlocked() && !isChallenged())
            player.updateCoins(coins);
    }
}
