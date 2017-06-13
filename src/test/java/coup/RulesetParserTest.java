package coup;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by connortaylorbrown on 4/06/17.
 */
public class RulesetParserTest {
    private Map<String, Action> actions;
    private Turn turn;
    private Player player;
    private Player target;
    private BlockAction blockAssassinate;

    @Before
    public void setUp() {
        player = new SimplePlayer("playa");
        target = new SimplePlayer("Clay Pigeon");
        turn = new Turn();
        player.setAction(turn);

        try {
            RulesetParser parser = new RulesetParser();
            parser.addCustomAction("exchange", new ExchangeAction());
            parser.read();
            actions = parser.getActions();

            TransitiveAction assassinate = (TransitiveAction)actions.get("assassinate");
            assassinate.setPlayer(player);
            assassinate.setTarget(target);
            turn.add(assassinate);

            blockAssassinate = (BlockAction)actions.get("block assassinate");
            blockAssassinate.setPlayer(target);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RulesetSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void assassinateReducesTargetInfluence() {
        int expectedInfluence = target.getInfluence() - 1;
        player.doAction();
        assertEquals(expectedInfluence, target.getInfluence());
    }

    @Test
    public void assassinateReducesCoins() {
        int expectedCoins = player.getCoins() - 3;
        player.doAction();
        assertEquals(expectedCoins, player.getCoins());
    }

    @Test
    public void blockAssassinatePreservesTargetInfluence() {
        int expectedInfluence = target.getInfluence();
        turn.add(blockAssassinate);
        player.doAction();
        assertEquals(expectedInfluence, target.getInfluence());
    }
}