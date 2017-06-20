package coup.restful;

import coup.Card;
import coup.Player;
import org.springframework.web.bind.annotation.RestController;

/**
 * Player implementation for use in online Coup
 */
@RestController
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
