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

    /**
     * Apply effect to current player
     */
    @Override
    public void execute() {
        player.updateCoins(coins);
    }
}
