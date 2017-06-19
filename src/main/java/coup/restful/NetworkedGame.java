package coup.restful;

import coup.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game implementation accessible as Spring Boot service online
 */
@RestController
public class NetworkedGame extends Game {
    public NetworkedGame() throws IOException, RulesetSyntaxException {
        RulesetParser parser = new RulesetParser();
        parser.addCustomAction("exchange", new ExchangeAction("exchange"));
        parser.read();

        this.players = new ArrayList<>();
        this.deck = parser.getDeck();
        this.rules = parser.getActions();
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<Player> getPlayers() {
        return players;
    }

    @Override
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public Player addPlayer(@RequestBody String name) {
        Player player = new NetworkedPlayer(name);
        player.setDeck(this.deck);
        player.pickUp(2);

        players.add(player);

        return player;
    }

    @RequestMapping(value = "/actions", method = RequestMethod.GET)
    public List<Action> getActions(@RequestParam(value = "name") String playerName) {
        Player player = getPlayerByName(playerName);
        List<Action> legalActions = new ArrayList<>();
        for(Action action: rules.values()) {
            if(action.canPerform(player, null)) legalActions.add(action);
        }
        return legalActions;
    }

    private Player getPlayerByName(String playerName) {
        for(Player player: players) {
            if(player.getName().equals(playerName)) return player;
        }
        return null;
    }

    @Override
    public Action addAction(Player player) {
        return null;
    }

    @Override
    public void addResponse(Player player, String command) {

    }

    @Override
    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public void play() {
        super.play();
    }
}
