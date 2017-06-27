package coup.restful;

import coup.ExchangeAction;
import coup.RulesetParser;
import coup.RulesetSyntaxException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
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

        NetworkedGame game = new NetworkedGame(hostPlayer, parser.getActions(), parser.getDeck());
        long gameID = counter.getAndIncrement();
        games.put(gameID, game);

        return gameID;
    }
}
