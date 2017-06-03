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
    RulesetParser parser;
    Map<String, Action> actions;
    Player player;
    Player target;

    @Before
    public void setUp() {
        player = new Player(3,2);
        target = new Player();

        try {
            parser = new RulesetParser();
            parser.read();
            actions = parser.getActions();

            TransitiveAction assassinate = (TransitiveAction)actions.get("assassinate");
            assassinate.setTarget(target);
            player.setAction(assassinate);
            player.doAction();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RulesetSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void assassinateReducesInfluence() {
        assertEquals(1, target.getInfluence());
    }

    @Test
    public void assassinateReducesCoins() {
        assertEquals(0, player.getCoins());
    }
}