package coup;

import java.util.Observable;

/**
 * Maintains player state as known to all players. As the identity of each card is not shared, the number in hand is
 * a strategic target referred to as influence. Player notifies observers when amount of coins or influence changes.
 */
public abstract class Player extends Observable {
    private String name;
    private int influence;
    private int coins;
    private Action action;
    protected Deck deck;

    /**
     * Initialise player with custom amount of coins and influence
     * @param name of player
     * @param coins starting amount
     * @param influence starting amount
     */
    public Player(String name, int coins, int influence) {
        this.name = name;
        this.coins = coins;
        this.influence = influence;
    }

    /**
     * Player starts with 2 coins and 2 influence according to vanilla rules.
     * @param name of player
     */
    public Player(String name) {
        this(name,2,2);
    }

    public String getName() {
        return name;
    }

    public int getInfluence() {
        return influence;
    }

    public int getCoins() {
        return coins;
    }

    /**
     * Increases the amount of coins by the given number. Assumes there are enough coins if increased by negative
     * amount.
     * @param coins to add
     */
    public void updateCoins(int coins) {
        this.coins += coins;
        if(coins != 0) this.setChanged();
        this.notifyObservers();
    }

    /**
     * Removes one of the player's cards.
     */
    public void removeInfluence() {
        this.influence--;
        this.setChanged();
        this.notifyObservers();
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
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

    public String toString() {
        return name;
    }
}
