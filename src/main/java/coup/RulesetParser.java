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
    private Map<String, Action> actions;

    public RulesetParser() throws FileNotFoundException {
        this.reader = new Scanner(new InputStreamReader(getClass().getResourceAsStream("/ruleset")));
        this.actions = new HashMap<>();
    }

    public Map<String, Action> getActions() {
        return actions;
    }

    public void read() throws IOException, RulesetSyntaxException {
        readActions();
    }

    private void readActions() throws IOException, RulesetSyntaxException {
        while(reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            if(line.isEmpty()) break;

            Scanner tokens = new Scanner(line).useDelimiter("[^0-9A-Za-z\\-]+");
            String name = tokens.next();
            if(tokens.hasNext()) readIntransitiveAction(name, tokens);
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
}
