package coup;

import org.junit.After;
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
        player = new Player();

        player.setAction(action);
        player.doAction();
    }

    @Test
    public void coinsIncreaseOnAction() {
        assertEquals(4, player.getCoins());
    }
}