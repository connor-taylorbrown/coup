package coup;

import java.util.Set;

/**
 * Allows player to invoke a challenge against whoever performed the last action.
 * Rules of a challenge:
 * - The target must require a specific card or cards to perform the action honestly.
 * - The target loses influence if they do not reveal a suitable card.
 * - The challenger loses influence otherwise
 */
public class ChallengeAction extends Action {
    /**
     * Set of cards which win challenge
     */
    private Set<String> cards;
    private Player target;
    private Action toChallenge;

    /**
     * Initialise challenge with set of cards target needs to win challenge, and the challenged action
     * @param cards needed to win challenge
     * @param toChallenge target action of this challenge
     */
    public ChallengeAction(Set<String> cards, Action toChallenge) {
        this.cards = cards;
        this.toChallenge = toChallenge;
    }

    /**
     * If the target reveals a card in the set, the challenger loses influence. If not, the target loses influence.
     */
    @Override
    public void execute() {
        this.target = toChallenge.player;   // Target is always the player of the challenged action

        if(cards.contains(target.reveal())) {
            toChallenge.setChallenged(false);
            player.removeInfluence();
        }
        else {
            toChallenge.setChallenged(true);
            target.removeInfluence();
        }
    }
}