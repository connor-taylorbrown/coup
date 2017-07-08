package coup.restful;

import coup.Card;
import coup.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Player implementation for use in online Coup
 */
public class NetworkedPlayer extends Player {
    @Autowired
    private SimpMessagingTemplate brokerMessagingTemplate;

    public NetworkedPlayer(String name) {
        super(name);
    }

    @Override
    public void pickUp(int number) {
        super.pickUp(number);
        this.brokerMessagingTemplate.convertAndSend("/topic/players/" + getName(), hand);
    }

    @Override
    public Card reveal() {
        return null;
    }

    @Override
    public void swap(int number) {

    }
}
