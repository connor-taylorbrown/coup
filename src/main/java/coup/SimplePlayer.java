package coup;

/**
 * Player class with minimal functionality
 */
public class SimplePlayer extends Player {
    public SimplePlayer(int coins, int influence) {
        super(coins, influence);
    }

    public SimplePlayer(){
        super();
    }

    @Override
    public String reveal() {
        return null;
    }
}
