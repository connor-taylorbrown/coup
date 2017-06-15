package coup;

import java.util.Collections;
import java.util.Set;
import java.util.Stack;

/**
 * Basic Court Deck allowing card exchange
 */
public class Deck {
    private Stack<String> contents;

    /**
     * Initialise deck with n copies of each given card
     * @param cards each type of card in deck
     * @param copies amount of each card in deck
     */
    public Deck(Set<String> cards, int copies) {
        this.contents = new Stack<>();
        populateContents(cards, copies);
        shuffle();
    }

    private void populateContents(Set<String> cards, int copies) {
        for(String card: cards) {
            for(int i = 0; i < copies; i++)
                contents.add(card);
        }
    }

    /**
     * Query whether deck has any cards
     * @return status of deck
     */
    public boolean isEmpty() {
        return contents.isEmpty();
    }

    /**
     * Get the top card off the deck if not empty
     * @return name of card
     */
    public String getCard() {
        return contents.pop();
    }

    /**
     * Shuffle a card into the deck
     * @param card to add
     */
    public void returnCard(String card) {
        contents.add(card);
        shuffle();
    }

    /**
     * Shuffle the deck
     */
    private void shuffle() {
        Collections.shuffle(contents);
    }
}
