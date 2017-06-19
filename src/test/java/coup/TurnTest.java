package coup;

import coup.textui.SimplePlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by connortaylorbrown on 5/06/17.
 */
public class TurnTest {
    private Player player;
    private Player blocker;
    private Action action;
    private BlockAction block;
    private Turn turn;

    @Before
    public void setUp() {
        turn = new Turn();

        player = new SimplePlayer("playa");
        blocker = new SimplePlayer("blocka");
        player.setAction(turn);

        action = new IntransitiveAction("foreignAid", 2);
        action.setPlayer(player);
        turn.add(action);

        block = new BlockAction("block foreignAid", action);
        block.setPlayer(blocker);
    }

    @Test
    public void singleActionTurnIncreasesCoins() {
        int expectedCoins = player.getCoins() + 2;
        player.doAction();
        assertEquals(expectedCoins, player.getCoins());
    }

    @Test
    public void blockedActionTurnDoesNothing() {
        int expectedCoins = player.getCoins();
        turn.add(block);
        player.doAction();
        assertEquals(expectedCoins, player.getCoins());
    }
}