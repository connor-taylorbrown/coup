package coup.textui;

import coup.Card;
import coup.Player;

import java.util.Scanner;

/**
 * Basic Player implementation for text-based interface
 */
public class SimplePlayer extends Player {

    public SimplePlayer(String name, int coins, int influence) {
        super(name, coins, influence);
    }

    public SimplePlayer(String name){
        this(name, 2,2);
    }

    private Card pickCard(String prompt) {
        Scanner input = new Scanner(System.in);
        String card;
        do {
            System.out.print(prompt);
            card = input.next();
        } while(!hand.contains(new Card(card)));
        return new Card(card);
    }

    @Override
    public Card reveal() {
        for(Card card: hand) System.out.println(card);
        Card card = pickCard(getName() + " reveals: ");
        hand.remove(card);

        this.setChanged();
        this.notifyObservers(card);

        return card;
    }

    @Override
    public void swap(int number) {
        System.out.println(getName() + " swaps " + number +  ":");
        for(Card card: hand) System.out.println("- " + card);
        for(int i = 0; i < number; i++)
            returnCard(pickCard("Return: "));
    }

    @Override
    public String toString() {
        return getName() + "\n" +
                "- " + getCoins() + " coins\n" +
                "- " + getInfluence() + " influence";
    }
}
