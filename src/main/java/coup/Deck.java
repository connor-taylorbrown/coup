package coup;

import java.util.Collections;
import java.util.Stack;

/**
 * Court Deck implemented with the Singleton Pattern.
 */
public class Deck {
    private static Deck theDeck;
    private Stack<String> contents;

    private Deck() {
        this.contents = new Stack<>();
    }

    /**
     * Return single instance of Deck for entire program
     * @return the deck
     */
    public static Deck getDeck() {
        if(theDeck == null) theDeck = new Deck();
        return theDeck;
    }

    /**
     * Get the top card off the deck
     * @return name of card
     */
    public String getCard() {
        return contents.pop();
    }

    /**
     * Add a card to the deck
     * @param card to add
     */
    public void returnCard(String card) {
        contents.add(card);
    }

    /**
     * Shuffle the deck
     */
    public void shuffle() {
        Collections.shuffle(contents);
    }
}
