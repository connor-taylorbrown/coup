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
        return lobby.createGame(hostPlayer);
    }
}
