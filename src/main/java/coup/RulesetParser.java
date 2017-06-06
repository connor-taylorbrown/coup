package coup;

import java.io.*;
import java.util.*;

/**
 * Converts the contents of the ruleset file into a set of actions for the rule engine to reference. The interpreter
 * pattern is inappropriate for this task, as this file is a declarative specification and no actions are actually
 * invoked.
 */
public class RulesetParser {
    private Scanner reader;
    private Map<String, Action> customActions;
    private Map<String, Action> actions;

    public RulesetParser() throws FileNotFoundException {
        this.reader = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/ruleset")));
        this.customActions = new HashMap<>();
        this.actions = new HashMap<>();
    }

    /**
     * Add a custom action to the ruleset
     * @param name to reference action
     * @param action object for execution
     */
    public void addCustomAction(String name, Action action) {
        customActions.put(name, action);
    }

    /**
     * Build action map from well-formed ruleset file
     * @throws IOException if problem with file system
     * @throws RulesetSyntaxException if unrecognised action
     */
    public void read() throws IOException, RulesetSyntaxException {
        readActions();
        readCards();
    }

    public Map<String, Action> getActions() {
        return actions;
    }

    private void readActions() throws IOException, RulesetSyntaxException {
        while(reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            if(line.isEmpty()) break;

            Scanner tokens = new Scanner(line).useDelimiter("[^0-9A-Za-z\\-]+");
            String name = tokens.next();
            if(!tokens.hasNext()) {
                Action customAction = customActions.get(name);
                if(customAction == null)
                    throw new RulesetSyntaxException("Unrecognised action type: " + name);
                actions.put(name, customAction);
            }
            else readIntransitiveAction(name, tokens);
        }
    }

    private void readIntransitiveAction(String name, Scanner tokens) {
        int coins = tokens.nextInt();
        if(!tokens.hasNext()) actions.put(name, new IntransitiveAction(coins));
        else readTransitiveAction(name, coins, tokens);
    }

    private void readTransitiveAction(String name, int coins, Scanner tokens) {
        int targetCoins = tokens.nextInt();
        boolean targetInfluence = tokens.next().toLowerCase().startsWith("y");
        actions.put(name, new TransitiveAction(coins, targetCoins, targetInfluence));
    }

    private void readCards() {
        while(reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            Scanner tokens = new Scanner(line).useDelimiter("[^A-Za-z]");

            String lookahead = tokens.next();
            if(lookahead.equals("block")) readBlockAction(tokens.next(), tokens);
            else readChallengeAction(lookahead, tokens);
        }
    }

    private void readChallengeAction(String name, Scanner tokens) {
        Set<String> cards = new HashSet<>();
        while (tokens.hasNext()) {
            cards.add(tokens.next());
        }
        // Directly challenge the action
        ChallengeAction challenge = new ChallengeAction(cards, actions.get(name));
        actions.put("challenge " + name, challenge);
    }

    private void readBlockAction(String name, Scanner tokens) {
        Set<String> cards = new HashSet<>();
        while (tokens.hasNext()) {
            cards.add(tokens.next());
        }
        // Directly block the action
        BlockAction block = new BlockAction(actions.get(name));
        actions.put("block " + name, block);
        // Challenge the block
        ChallengeAction challenge = new ChallengeAction(cards, block);
        actions.put("challenge block " + name, challenge);
    }
}
