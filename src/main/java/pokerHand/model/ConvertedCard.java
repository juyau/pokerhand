package pokerHand.model;

public class ConvertedCard {
    private int value;
    private Character suit;

    public ConvertedCard() {
    }
    public ConvertedCard(int value, Character suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Character getSuit() {
        return suit;
    }

    public void setSuit(Character suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "ConvertedCard{" +
                "value=" + value +
                ", suit=" + suit +
                '}';
    }

}
