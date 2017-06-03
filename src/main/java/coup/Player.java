package coup;

/**
 * Maintains player state as known to all players. As the identity of each card is not shared, the number in hand is
 * a strategic target referred to as influence.
 */
public class Player {
    private int influence;
    private int coins;
    private Action action;

    public Player(int coins, int influence) {
        this.coins = coins;
        this.influence = influence;
    }

    /**
     * Player starts with 2 coins according to standard rules.
     */
    public Player() {
        this(2,2);
    }

    public int getInfluence() {
        return influence;
    }

    public int getCoins() {
        return coins;
    }

    /**
     * Increases the amount of coins by the given number.
     * @param coins to add
     */
    public void updateCoins(int coins) {
        this.coins += coins;
    }

    /**
     * Removes one of the player's cards.
     */
    public void removeInfluence() {
        this.influence--;
    }

    /**
     * Set action to perform and sign up player as receiver
     * @param action to perform
     */
    public void setAction(Action action) {
        action.setPlayer(this);
        this.action = action;
    }

    /**
     * Perform the current action
     */
    public void doAction() {
        action.execute();
    }
}
