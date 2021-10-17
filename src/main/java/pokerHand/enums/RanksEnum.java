package pokerHand.enums;

public enum RanksEnum {
    HIGH_CARD(1),
    PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_A_KING(4),
    STRAIGHT(5),
    FLUSH(6),
    FULL_HOUSE(7),
    FOUR_OF_A_KIND(8),
    STRAIGHT_FLUSH(9),
    ROYAL_FLUSH(10);

    private final Integer rank;

    RanksEnum(final Integer rank) {
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }
}
