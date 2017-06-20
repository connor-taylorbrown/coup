package coup;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Allows player to invoke a challenge against whoever performed the last action. Observes target player, updates and
 * detaches when they reveal a card.
 * Rules of a challenge:
 * - The target must require a specific card or cards to perform the action honestly.
 * - The target loses influence if they do not reveal a suitable card.
 * - The challenger loses influence otherwise
 */
public class ChallengeAction extends Action implements Observer {
    /**
     * Set of cards which win challenge
     */
    private Set<String> legalCards;
    private Action toChallenge;

    /**
     * Initialise challenge with set of cards target needs to win challenge, and the challenged action
     * @param label action label
     * @param cards needed to win challenge
     * @param toChallenge target action of this challenge
     */
    public ChallengeAction(String label, Set<String> cards, Action toChallenge) {
        this.label = label;
        this.legalCards = cards;
        this.toChallenge = toChallenge;
    }

    @Override
    public boolean canPerform(Player player, Action trigger) {
        return trigger == toChallenge;
    }

    /**
     * Request target to reveal a card, wait for response.
     */
    @Override
    public void execute() {
        Player target = toChallenge.player;   // Target is always the player of the challenged action

        target.addObserver(this);
        target.reveal();
    }

    /**
     * Runs when the target has revealed a card. If the card is legal, the target swaps it and the challenger forfeits
     * a card. If not, the target loses the revealed card.
     * @param o target player
     * @param arg revealed card
     */
    @Override
    public void update(Observable o, Object arg) {
        Player target = (Player)o;
        Card revealed = (Card)arg;

        if(revealed != null) {
            target.deleteObserver(this);
            if (legalCards.contains(revealed.getName())) {
                toChallenge.setChallenged(false);

                player.removeInfluence();
                player.reveal();

                target.returnCard(revealed);
                target.pickUp(1);
            } else {
                toChallenge.setChallenged(true);

                target.removeInfluence();
            }
        }
    }
}
