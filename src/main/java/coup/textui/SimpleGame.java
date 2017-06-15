package coup.textui;

import coup.*;

import java.util.*;

/**
 * Basic Game implementation for text-based interface
 */
public class SimpleGame extends Game implements Observer {
    private Map<String, Action> rules;

    public SimpleGame(Deck deck, Map<String, Action> rules) {
        this.players = new ArrayList<>();
        this.deck = deck;
        this.rules = rules;
    }

    /**
     * Add a player of type SimplePlayer after picking up starting hand
     * @param name of player to add
     */
    @Override
    public void addPlayer(String name) {
        Player player = new SimplePlayer(name);
        player.setDeck(this.deck);
        player.pickUp(2);

        player.addObserver(this);
        players.add(player);
    }

    @Override
    protected void addAction(Player player) {
        Map<String, Player> activePlayers = getActivePlayers();
        Map<String, Action> availableActions = getAvailableActions(player);

        Scanner input = new Scanner(System.in);
        System.out.print(player.getName() + ": ");

        String[] command = input.nextLine().split("\\s+");
        Action action = availableActions.get(command[0]);

        if(command.length == 2) {
            ((TransitiveAction)action).setTarget(activePlayers.get(command[1]));
        }

        action.setPlayer(player);
        turn.add(action);
    }

    private Map<String,Action> getAvailableActions(Player player) {
        Map<String, Action> availableActions = new HashMap<>();
        for(String verb: rules.keySet()) {
            Action action = rules.get(verb);
            if(action.canPerform(player, null)) {
                availableActions.put(verb, action);
            }
        }
        return availableActions;
    }

    private Map<String,Player> getActivePlayers() {
        Map<String, Player> activePlayers = new HashMap<>();
        for(Player player: players) {
            if(player.getInfluence() > 0) activePlayers.put(player.getName(), player);
        }
        return activePlayers;
    }

    @Override
    public void update(Observable o, Object arg) {
        Player player = (Player)o;

        System.out.println(player.getName());
        System.out.println("- " + player.getCoins() + " coins");
        System.out.println("- " + player.getInfluence() + " influence");
    }
}
