package coup.textui;

import coup.ExchangeAction;
import coup.Game;
import coup.RulesetParser;
import coup.RulesetSyntaxException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Set up and play basic game of Coup with text-based interface
 */
public class TextUI {
    public static void main(String[] args) {
        try {
            RulesetParser parser = new RulesetParser();
            parser.addCustomAction("exchange", new ExchangeAction("exchange"));
            parser.read();

            Game game = new SimpleGame(parser.getDeck(), parser.getActions());

            game.addPlayer("Connor");
            game.addPlayer("Josh");
            game.addPlayer("Marc");

            game.play();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (RulesetSyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
