package coup.restful;

import coup.Player;

/**
 * Player implementation for NetworkedGame service
 */
public class NetworkedPlayer extends Player {
    public NetworkedPlayer(String name) {
        super(name);
    }

    @Override
    public String reveal() {
        return null;
    }

    @Override
    public void returnCard(String card) {

    }

    @Override
    public void pickUp(int number) {

    }

    @Override
    public void swap(int number) {

    }
}
