package coup.restful;

import coup.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Game implementation accessible as Spring Boot service online
 */
public class NetworkedGame extends Game {
    private String host;

    public NetworkedGame(String host, Map<String, Action> rules, Deck deck) {
        this.host = host;
        this.rules = rules;
        this.deck = deck;
        this.players = new ArrayList<>();
    }

    @Override
    public Player addPlayer(String name) {
        Player player = new NetworkedPlayer(name);
        player.setDeck(this.deck);
        player.pickUp(2);

        players.add(player);

        return player;
    }

    @Override
    public Action addAction(Player player) {
        return null;
    }

    @Override
    public void addResponse(Player player, String command) {

    }

    @Override
    public String toString() {
        return String.format("Game[host='%s']", host);
    }
}
