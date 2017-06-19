package coup.textui;

import coup.*;

import java.util.*;

/**
 * Basic Game implementation for text-based interface
 */
public class SimpleGame extends Game implements Observer {
    public SimpleGame(Deck deck, Map<String, Action> rules) {
        this.players = new ArrayList<>();
        this.deck = deck;
        this.rules = rules;
    }

    /**
     * Add a player of type SimplePlayer after picking up starting hand
     * @param name of player to add
     * @return player object added
     */
    @Override
    public Player addPlayer(String name) {
        Player player = new SimplePlayer(name);
        player.setDeck(this.deck);
        player.pickUp(2);

        player.addObserver(this);
        players.add(player);

        return player;
    }

    @Override
    public Action addAction(Player player) {
        String[] command = getCommand(player).split("\\s+");
        Action action = getAvailableActions(player).get(command[0]);

        if(command.length == 2) {
            ((TransitiveAction)action).setTarget(getActivePlayers().get(command[1]));
        }

        action.setPlayer(player);
        turn.add(action);

        // Ask players if they wish to block or challenge
        if(!checkResponse("block " + command[0]))
            checkResponse("challenge " + command[0]);

        return action;
    }

    private String getCommand(Player player) {
        Scanner input = new Scanner(System.in);

        System.out.println("====================");
        for(Player eachPlayer: players) System.out.println(eachPlayer);
        for(String action: getAvailableActions(player).keySet()) System.out.println(action);
        System.out.println();
        for(Card card: player.getHand()) System.out.println(card);
        System.out.print(player.getName() + ": ");

        return input.nextLine();
    }

    @Override
    public void addResponse(Player player, String command) {
        Action action = rules.get(command);
        action.setPlayer(player);
        turn.add(action);

        checkResponse("challenge " + command);
    }

    private boolean checkResponse(String command) {
        if(canRespond(command)) {
            Scanner input = new Scanner(System.in);

            System.out.print(command + "? ");
            String name = input.nextLine().trim();
            if(!name.isEmpty()) {
                Player player = getActivePlayers().get(name);
                addResponse(player, command);
                return true;
            }
        }
        return false;
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

        System.out.println(player.toString());
    }
}
