package coup;

import java.io.*;
import java.util.*;

/**
 * Converts the contents of the ruleset file into a set of actions for the rule engine to reference. The interpreter
 * pattern is inappropriate for this task, as this file is a declarative specification and no actions are actually
 * invoked.
 * All rules applied are drawn from the resources/ruleset file at start. Custom Action classes can be added to the
 * parser to support expansion packs, but if not named in the file they will not be used.
 */
public class RulesetParser {
    private Scanner reader;
    private Map<String, Action> customActions;

    private Set<String> cardNames;
    private Map<String, Action> actions;

    public RulesetParser() throws FileNotFoundException {
        this.reader = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/ruleset")));
        this.customActions = new HashMap<>();
        this.actions = new HashMap<>();
        this.cardNames = new HashSet<>();
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
     * @throws RulesetSyntaxException if unrecognised action or undeclared card
     */
    public void read() throws IOException, RulesetSyntaxException {
        readCardNames();
        readActions();
        readCardActions();
    }

    public Deck getDeck() {
        return new Deck(cardNames, 3);
    }

    public Map<String, Action> getActions() {
        return actions;
    }

    private void readCardNames() {
        Scanner tokens = new Scanner(reader.nextLine().trim()).useDelimiter(",\\s*");
        while(tokens.hasNext()) {
            String card = tokens.next();
            cardNames.add(card);
        }
        reader.nextLine();
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

    private void readCardActions() throws RulesetSyntaxException {
        while(reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            Scanner tokens = new Scanner(line).useDelimiter(" |:\\s*");

            String lookahead = tokens.next();
            if(lookahead.equals("block")) readBlockAction(tokens.next(), tokens.next());
            else readChallengeAction(lookahead, tokens.next());
        }
    }

    private Set<String> getCards(String cardText) throws RulesetSyntaxException {
        Set<String> cards = new HashSet<>();
        Scanner tokens = new Scanner(cardText).useDelimiter(",\\s*");
        while (tokens.hasNext()) {
            String card = tokens.next();
            if(cardNames.contains(card)) cards.add(card);
            else throw new RulesetSyntaxException("Undeclared card: " + card);
        }
        return cards;
    }

    private void readChallengeAction(String name, String cardText) throws RulesetSyntaxException {
        Set<String> cards = getCards(cardText);
        // Directly challenge the action
        ChallengeAction challenge = new ChallengeAction(cards, actions.get(name));
        actions.put("challenge " + name, challenge);
    }

    private void readBlockAction(String name, String cardText) throws RulesetSyntaxException {
        Set<String> cards = getCards(cardText);
        // Directly block the action
        BlockAction block = new BlockAction(actions.get(name));
        actions.put("block " + name, block);
        // Challenge the block
        ChallengeAction challenge = new ChallengeAction(cards, block);
        actions.put("challenge block " + name, challenge);
    }
}
