package coup;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class with minimal functionality
 */
public class SimplePlayer extends Player {
    private List<String> hand;
    private Deck deck;

    public SimplePlayer(String name, int coins, int influence) {
        super(name, coins, influence);
        this.hand = new ArrayList<>();
    }

    public SimplePlayer(String name){
        this(name, 2,2);
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public String getName() {
        return name;
    }

    @Override
    public String reveal() {
        return null;
    }

    @Override
    public void returnCard(String revealed) {
        hand.remove(revealed);
        deck.returnCard(revealed);
    }

    @Override
    public void pickUp(int number) {
        for(int i = 0; i < number; i++)
            hand.add(deck.getCard());
    }

    @Override
    public void swap(int number) {

    }

    public String toString() {
        return name;
    }
}
