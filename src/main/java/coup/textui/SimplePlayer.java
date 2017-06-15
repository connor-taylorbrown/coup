package coup.textui;

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

    private String pickCard(String prompt) {
        Scanner input = new Scanner(System.in);
        String card = null;
        while(!hand.contains(card)) {
            System.out.print(prompt);
            card = input.next();
        }
        return card;
    }

    @Override
    public String reveal() {
        for(String card: hand) System.out.println(card);
        String card = pickCard(getName() + " reveals: ");
        hand.remove(card);
        return card;
    }

    @Override
    public void returnCard(String card) {
        hand.remove(card);
        deck.returnCard(card);
    }

    @Override
    public void pickUp(int number) {
        for(int i = 0; i < number; i++) {
            String card = deck.getCard();
            hand.add(card);
            System.out.println(getName() + " picks up " + card);
        }
    }

    @Override
    public void swap(int number) {
        System.out.println(getName() + " swaps " + number +  ":");
        for(String card: hand) System.out.println("- " + card);
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
