package coup;

/**
 * Facilitates the exchange of 2 specific cards with the Court Deck. As this action does not affect coins and influence,
 * it requires a unique implementation.
 */
public class ExchangeAction extends Action {
    public ExchangeAction(String label) {
        this.label = label;
    }

    @Override
    public boolean canPerform(Player player, Action trigger) {
        return true;
    }

    @Override
    public void execute() {
        if(!isChallenged()) {
            player.pickUp(2);
            player.swap(2);
        }
    }
}
