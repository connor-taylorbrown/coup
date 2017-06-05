package coup;

/**
 * Allows a player to block the last action.
 */
public class BlockAction extends Action {
    private Action toBlock;

    public BlockAction(Action toBlock) {
        this.toBlock = toBlock;
    }

    /**
     * Block the action if this block is not successfully challenged
     */
    @Override
    public void execute() {
        if(!isChallenged()) toBlock.setBlocked(true);
    }
}
