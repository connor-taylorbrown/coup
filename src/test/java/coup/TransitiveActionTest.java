package coup;

import coup.textui.SimplePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by connortaylorbrown on 3/06/17.
 */
public class TransitiveActionTest {
    TransitiveAction action;
    Player player;
    Player target;

    @Before
    public void setUp() {
        action = new TransitiveAction("steal",2,-2,false);
        player = new SimplePlayer("playa");
        target = new SimplePlayer("Clay Pigeon");

        action.setTarget(target);

        player.setAction(action);
        player.doAction();
    }

    @Test
    public void coinsIncreaseOnAction() {
        assertEquals(4, player.getCoins());
    }

    @Test
    public void targetCoinsDecreaseOnAction() {
        assertEquals(0, target.getCoins());
    }

    @Test
    public void targetInfluenceUnchanged() {
        assertEquals(2, target.getInfluence());
    }
}