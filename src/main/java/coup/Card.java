package coup;

/**
 * Card class allowing players to show and hide cards from other players according to the rules of Coup.
 */
public class Card {
    private String name;
    private boolean revealed;

    /**
     * Constructor for card given name, initialised as hidden
     * @param name of the card
     */
    public Card(String name) {
        this.name = name;
        this.revealed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public String toString() {
        return this.name;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object o) {
        return o != null && o instanceof Card && o.hashCode() == this.hashCode() && !((Card)o).isRevealed();
    }
}
