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
     * @return player object added
     */
    public abstract Player addPlayer(String name);

    /**
     * Adds an action to the current turn to be performed by the given player.
     * @param player to perform action
     * @return action sought by the player
     * @see Turn
     */
    public abstract Action addAction(Player player);

    /**
     * Adds a player's response to the last action
     * @param player responding to last action
     * @param command type of response
     */
    public abstract void addResponse(Player player, String command);

    /**
     * Checks whether the given response to the last action is legal
     * @param response to the previous action
     * @return true if response is legal
     */
    public boolean canRespond(String response) {
        return rules.containsKey(response);
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
     * Deal two cards at start of game per standard rules
     */
    private void deal() {
        for(Player player: players) player.pickUp(2);
    }

    /**
     * Play a game of Coup until finished.
     * - Players take turns in listed order
     * - Each turn the assigned player adds their action
     * - The action performed depends on the responses of other players
     */
    public void play() {
        deal();
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
