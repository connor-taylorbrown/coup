package coup;

/**
 * Maintains player state as known to all players. As the identity of each card is not shared, the number in hand is
 * a strategic target referred to as influence.
 */
public abstract class Player {
    private int influence;
    private int coins;
    private Action action;

    /**
     * Initialise player with custom amount of coins and influence
     * @param coins starting amount
     * @param influence starting amount
     */
    public Player(int coins, int influence) {
        this.coins = coins;
        this.influence = influence;
    }

    /**
     * Player starts with 2 coins and 2 influence according to vanilla rules.
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

    /**
     * Reveal a card in hand to the table
     * @return name of the card
     */
    public abstract String reveal();

    /**
     * Remove a named card from the hand and return it to the Court Deck after being revealed
     * @param card name of the card
     */
    public abstract void returnCard(String card);

    /**
     * Pick up a number of cards from the Court Deck
     * @param number of cards to pick up
     */
    public abstract void pickUp(int number);

    /**
     * Swap a number of cards in the hand with the Court Deck. Must have at least that number of cards in hand.
     * @param number of cards to swap
     */
    public abstract void swap(int number);
}
