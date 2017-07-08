package coup.restful;

import coup.ExchangeAction;
import coup.Player;
import coup.RulesetParser;
import coup.RulesetSyntaxException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Persistent service for creating games and routing commands from clients.
 */
@Service
public class LobbyService {
    private Map<Long, NetworkedGame> games;
    private final AtomicLong counter;

    public LobbyService() {
        this.counter = new AtomicLong();
        this.games = new HashMap<>();
    }

    /**
     * Create a new game in response to a request from a host player
     * @param hostPlayer name of the player requesting the game
     * @return unique identifier for the created game
     * @throws IOException if ruleset cannot be read
     * @throws RulesetSyntaxException if ruleset cannot be read
     */
    public long createGame(String hostPlayer) throws IOException, RulesetSyntaxException {
        RulesetParser parser = new RulesetParser();
        parser.addCustomAction("exchange", new ExchangeAction("exchange"));
        parser.read();

        long gameID = counter.getAndIncrement();
        NetworkedGame game = new NetworkedGame(gameID, hostPlayer, parser.getActions(), parser.getDeck());
        games.put(gameID, game);

        return gameID;
    }

    /**
     * Looks up and returns a game given an id.
     * @param id unique identifier for a game
     * @return game with given id or null
     */
    public NetworkedGame getGame(long id) {
        return games.get(id);
    }

    /**
     * Creates a new player of given name on game of given id. It is the caller's responsibility to ensure the game
     * exists.
     * @param playerName name for new player
     * @param gameID id for existing game
     * @return newly created player object
     */
    public Player joinGame(String playerName, long gameID) {
        return getGame(gameID).addPlayer(playerName);
    }

    /**
     * Returns a list of players added to the given game.
     * @param gameID id for request game
     * @return list of players
     */
    public List<Player> getPlayers(long gameID) {
        return getGame(gameID).getPlayers();
    }
}
