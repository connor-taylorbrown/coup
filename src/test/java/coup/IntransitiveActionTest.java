package coup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by connortaylorbrown on 3/06/17.
 */
public class IntransitiveActionTest {
    private Action action;
    private Player player;

    @Before
    public void setUp() {
        action = new IntransitiveAction(2);
        player = new SimplePlayer("playa");

        player.setAction(action);
    }

    @Test
    public void coinsIncreaseOnAction() {
        int expectedCoins = player.getCoins() + 2;
        player.doAction();
        assertEquals(expectedCoins, player.getCoins());
    }
}