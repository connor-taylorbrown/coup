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
    private Set<String> legalCards;
    private Action toChallenge;

    /**
     * Initialise challenge with set of cards target needs to win challenge, and the challenged action
     * @param cards needed to win challenge
     * @param toChallenge target action of this challenge
     */
    public ChallengeAction(Set<String> cards, Action toChallenge) {
        this.legalCards = cards;
        this.toChallenge = toChallenge;
    }

    @Override
    public boolean canPerform(Player player, Action trigger) {
        return trigger == toChallenge;
    }

    /**
     * If the target reveals a card in the set, the target swaps it and the challenger forfeits a card.
     * If not, the target loses the revealed card.
     */
    @Override
    public void execute() {
        Player target = toChallenge.player;   // Target is always the player of the challenged action

        Card revealed = target.reveal();
        if(legalCards.contains(revealed.getName())) {
            toChallenge.setChallenged(false);

            player.removeInfluence();
            player.reveal();

            target.returnCard(revealed);
            target.pickUp(1);
        }
        else {
            toChallenge.setChallenged(true);

            target.removeInfluence();
        }
    }
}
