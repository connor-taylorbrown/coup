package coup.restful;

import coup.Card;
import coup.Player;

/**
 * Player implementation for use in online Coup
 */
public class NetworkedPlayer extends Player {
    public NetworkedPlayer(String name) {
        super(name);
    }

    @Override
    public Card reveal() {
        return null;
    }

    @Override
    public void swap(int number) {

    }
}
