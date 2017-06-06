package coup;

import java.util.List;

/**
 * Player class with minimal functionality
 */
public class SimplePlayer extends Player {
    public SimplePlayer(int coins, int influence) {
        super(coins, influence);
    }

    public SimplePlayer(){
        super();
    }

    @Override
    public String reveal() {
        return null;
    }

    @Override
    public void returnCard(String revealed) {

    }

    @Override
    public void pickUp(int number) {

    }

    @Override
    public void swap(int number) {

    }
}
