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
        parser.addCustomAction("exchange", new ExchangeAction());
        parser.read();

        this.players = new ArrayList<>();
        this.deck = parser.getDeck();
        this.rules = parser.getActions();
    }

    @RequestMapping(value = "/players", method = RequestMethod.GET)
    public List<NetworkedPlayer> getPlayers() {
        List<NetworkedPlayer> networkedPlayers = new ArrayList<>();
        for(Player player: players)
            networkedPlayers.add((NetworkedPlayer)player);
        return networkedPlayers;
    }

    @Override
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public void addPlayer(@RequestBody String name) {
        Player player = new NetworkedPlayer(name);
        player.setDeck(this.deck);
        player.pickUp(2);

        players.add(player);
    }

    @Override
    protected void addAction(Player player) {

    }

    @Override
    protected String getCommand(Player player) {
        return null;
    }

    @Override
    protected boolean checkResponse(String response) {
        return false;
    }
}
