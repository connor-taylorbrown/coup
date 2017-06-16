package coup.restful;

import coup.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Game implementation accessible as REST service 
 */
@Path("/game")
public class NetworkedGame extends Game {
    public NetworkedGame() throws IOException, RulesetSyntaxException {
        RulesetParser parser = new RulesetParser();
        parser.addCustomAction("exchange", new ExchangeAction());
        parser.read();

        this.players = new ArrayList<>();
        this.players.add(new NetworkedPlayer("Connor"));
        this.deck = parser.getDeck();
        this.rules = parser.getActions();
    }

    @GET
    @Path("/players")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getPlayers() {
        System.out.println("Found " + players.size() + " players");
        return this.players;
    }

    @Override
    @POST
    @Path("/addPlayer")
    public void addPlayer(String name) {
        System.out.println(name);
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

    @Override
    @POST
    @Path("/play")
    public void play() {
        super.play();
    }
}
