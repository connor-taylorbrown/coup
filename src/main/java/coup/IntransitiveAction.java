package coup;

/**
 * Performs action affecting only the current player.
 */
public class IntransitiveAction extends Action {
    private int coins;

    /**
     * Initialise effect of action on current player coins
     * @param coins for current player
     */
    public IntransitiveAction(int coins) {
        this.coins = coins;
    }

    @Override
    public boolean canPerform(Player player, Action trigger) {
        return player.getCoins() + coins >= 0;
    }

    /**
     * Apply effect to current player
     */
    @Override
    public void execute() {
        if(!isBlocked() && !isChallenged())
            player.updateCoins(coins);
    }
}
