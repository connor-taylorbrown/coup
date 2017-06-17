package coup;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by connortaylorbrown on 6/06/17.
 */
public class DeckTest {
    Set<String> cards;
    Deck deck;

    @Before
    public void setUp() {
        cards = new HashSet<>();
        cards.add("duke");
        cards.add("ambassador");
        deck = new Deck(cards, 3);
    }

    @Test
    public void deckContainsThreeOfEach() {
        int count = 0;
        while(!deck.isEmpty()) {
            deck.getCard();
            count++;
        }
        assertEquals(cards.size() * 3, count);
    }

    @Test
    public void cardHiddenAfterReturnToDeck() {
        cards = new HashSet<>();
        cards.add("duke");
        deck = new Deck(cards, 1);

        Card card = deck.getCard();
        card.setRevealed(true);
        deck.returnCard(card);
        assertFalse(deck.getCard().isRevealed());
    }
}