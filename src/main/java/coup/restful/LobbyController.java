package coup.restful;

import coup.RulesetSyntaxException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
        System.out.println(hostPlayer);
        return lobby.createGame(hostPlayer);
    }

    @RequestMapping(value = "/game/{gameID}", method = RequestMethod.GET)
    public NetworkedGame getGame(@PathVariable(value="gameID") long id) {
        return lobby.getGame(id);
    }

    @RequestMapping(value = "/game/{gameID}/join", method = RequestMethod.POST)
    public NetworkedPlayer joinGame(@RequestBody String player, @PathVariable(value="gameID") long game) {
        if(lobby.getGame(game) == null) throw new ResourceNotFoundException("That game does not appear to exist");
        return lobby.joinGame(player, game);
    }
}
