package coup;

/**
 * Allows a player to block the last action.
 */
public class BlockAction extends Action {
    private Action toBlock;

    public BlockAction(String label, Action toBlock) {
        this.label = label;
        this.toBlock = toBlock;
    }

    @Override
    public boolean canPerform(Player player, Action trigger) {
        return trigger == toBlock;
    }

    /**
     * Block the action if this block is not successfully challenged
     */
    @Override
    public void execute() {
        if(!isChallenged()) toBlock.setBlocked(true);
    }
}
