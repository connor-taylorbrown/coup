package coup.textui;

import coup.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic Player implementation for text-based interface
 */
public class SimplePlayer extends Player {
    private List<String> hand;

    public SimplePlayer(String name, int coins, int influence) {
        super(name, coins, influence);
        this.hand = new ArrayList<>();
    }

    public SimplePlayer(String name){
        this(name, 2,2);
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
        for(int i = 0; i < number; i++) {
            String card = deck.getCard();
            hand.add(card);
        }
    }

    @Override
    public void swap(int number) {

    }
}
