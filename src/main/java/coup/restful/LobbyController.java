package coup.restful;

import coup.Player;
import coup.RulesetSyntaxException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * RESTful Spring Boot driven interface between web client and LobbyService.
 */
@RestController
public class LobbyController {
    private LobbyService lobby;

    public LobbyController() {
        lobby = new LobbyService();
    }

    @RequestMapping(value = "/game/create", method = RequestMethod.POST)
    public long createGame(@RequestBody String hostPlayer) throws IOException, RulesetSyntaxException {
        return lobby.createGame(hostPlayer);
    }

    @RequestMapping(value = "/game/{gameID}", method = RequestMethod.GET)
    public NetworkedGame getGame(@PathVariable(value="gameID") long id) {
        return lobby.getGame(id);
    }

    @RequestMapping(value = "/game/{gameID}/players", method = RequestMethod.GET)
    public List<Player> getPlayers(@PathVariable long gameID) throws ResourceNotFoundException {
        if(lobby.getGame(gameID) == null) throw new ResourceNotFoundException();
        return lobby.getPlayers(gameID);
    }

    @SuppressWarnings("unused")
    @MessageMapping(value = "/game/{gameID}/join")
    @SendTo("/topic/players/{gameID}")
    public Player joinGame(String player, @DestinationVariable long gameID) throws ResourceNotFoundException {
        if(lobby.getGame(gameID) == null) throw new ResourceNotFoundException();
        return lobby.joinGame(player, gameID);
    }
}
