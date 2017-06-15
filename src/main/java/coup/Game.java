package coup;

import java.util.List;
import java.util.Map;

/**
 * Basic Coup game structure, allows addAction to be implemented depending on user interface. A game is played by two
 * or more players, who take turns in listed order, adding their initial action then performing it, taking into account
 * the responses of their opponents. Game classes are also Factories of an Abstract Factory pattern, producing Player
 * objects of the appropriate class for the interface. This implementation of the pattern is slightly modified, as
 * Players are not returned but added to a list.
 */
public abstract class Game {
    /**
     * Turn executed with every game cycle
     * @see Turn
     */
    protected Turn turn;
    /**
     * All starting players in order of turns
     */
    protected List<Player> players;
    /**
     * Court deck from which all cards are taken and returned to
     * @see Deck
     */
    protected Deck deck;
    /**
     * Index of actions applicable to the current game
     * @see Action
     */
    protected Map<String, Action> rules;

    /**
     * Adds a player of the appropriate type for the game interface.
     * @param name of player to add
     */
    public abstract void addPlayer(String name);

    /**
     * Adds an action to the current turn to be performed by the given player.
     * @param player to perform action
     * @see Turn
     */
    protected abstract void addAction(Player player);

    /**
     * Gets the command from the player for the initial action in the turn.
     * @param player whose turn it is to start
     * @return command for the player's action
     */
    protected abstract String getCommand(Player player);

    /**
     * Checks whether any player wishes to respond to the last action, adds their response and returns true if so
     * @param response to a previous action
     * @return true if response is legal and requested
     */
    protected abstract boolean checkResponse(String response);

    /**
     * Adds a player's response to the last action
     * @param player responding to last action
     * @param command type of response
     */
    protected void addResponse(Player player, String command) {
        Action action = rules.get(command);
        action.setPlayer(player);
        turn.add(action);

        checkResponse("challenge " + command);
    }

    /**
     * A game is finished if only one player has non-zero influence. Assumes one or more players.
     * @return true if this holds
     */
    private boolean finished() {
        int contenders = 0;
        for(Player player: players) {
            if(player.getInfluence() > 0) contenders++;
            if(contenders > 1) return false;
        }
        // Reaches here if never any more than 1 contender
        return true;
    }

    /**
     * Play a game of Coup until finished.
     * - Players take turns in listed order
     * - Each turn the assigned player adds their action
     * - The action performed depends on the responses of other players
     */
    public final void play() {
        while(!finished()) {
            for(Player player: players) {
                // Skip eliminated players
                if(player.getInfluence() > 0) {
                    // Next player starts a new turn
                    turn = new Turn();

                    // Turn consists at least of an action. Player then executes turn
                    addAction(player);
                    player.setAction(turn);
                    player.doAction();
                }
            }
        }
    }
}
