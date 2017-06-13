package coup;

import java.util.List;

/**
 * Basic Coup game structure, allows addAction to be implemented depending on user interface. A game is played by two
 * or more players, who take turns in listed order, adding their initial action then performing it, taking into account
 * the responses of their opponents. Game classes are also Factories of an Abstract Factory pattern, producing Player
 * objects of the appropriate class for the interface. This implementation of the pattern is slightly modified, as
 * Players are not returned but added to a list.
 */
public abstract class Game {
    private Turn turn;
    private List<Player> players;

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
