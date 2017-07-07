package coup.restful;

import coup.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Game implementation accessible as Spring Boot service online
 */
public class NetworkedGame extends Game {
    private long id;
    private String host;

    public NetworkedGame(long id, String host, Map<String, Action> rules, Deck deck) {
        this.id = id;
        this.host = host;
        this.rules = rules;
        this.deck = deck;
        this.players = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getHost() {
        return host;
    }

    public List<Player> getPlayers() {
        return players;
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
