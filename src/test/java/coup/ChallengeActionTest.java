package coup;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by connortaylorbrown on 5/06/17.
 */
public class ChallengeActionTest {
    private Player player;
    private Player target;

    @Before
    public void setUp() {
        Set<String> cards = new HashSet<>();
        cards.add("Ambassador");
        cards.add("Captain");

        player = new Player("playa") {
            @Override
            public Card reveal() {
                return null;
            }

            @Override
            public void swap(int number) {

            }
        };
        target = mock(Player.class);

        Action challenged = new TransitiveAction("steal", 2,-2,false);
        ChallengeAction challenge = new ChallengeAction("challenge steal", cards, challenged);

        challenged.setPlayer(target);
        player.setAction(challenge);
    }

    @Test
    public void playerLosesInfluenceGivenCorrectCard() {
        int expectedInfluence = player.getInfluence() - 1;

        when(target.reveal()).thenReturn(new Card("Ambassador"));
        player.doAction();
        assertEquals(expectedInfluence, player.getInfluence());
    }

    @Test
    public void playerRetainsInfluenceGivenWrongCard() {
        int expectedInfluence = player.getInfluence();

        when(target.reveal()).thenReturn(new Card("Contessa"));
        player.doAction();
        assertEquals(expectedInfluence, player.getInfluence());
    }
}