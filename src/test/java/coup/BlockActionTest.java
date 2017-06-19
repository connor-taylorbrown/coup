package coup;

import coup.textui.SimplePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by connortaylorbrown on 5/06/17.
 */
public class BlockActionTest {
    private Action action;
    private Player player;

    @Before
    public void setUp() {
        action = new IntransitiveAction("foreignAid", 2);
        player = new SimplePlayer("playa");

        player.setAction(action);
    }

    @Test
    public void blockedActionDoesNothing() {
        BlockAction block = new BlockAction("block foreignAid", action);
        block.execute();

        int expectedCoins = player.getCoins();
        player.doAction();
        assertEquals(expectedCoins, player.getCoins());
    }

}