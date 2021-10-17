package pokerHand.model;

import java.util.List;

public class Hand {
    private List<ConvertedCard> cards;
    private int rank;
    private int rankValue;
    private int[] highCards;

    public Hand() {
    }

    public Hand(List<ConvertedCard> cards, int rank, int rankValue, int[] highCards) {
        this.cards = cards;
        this.rank = rank;
        this.rankValue = rankValue;
        this.highCards = highCards;
    }

    public List<ConvertedCard> getCards() {
        return cards;
    }

    public void setCards(List<ConvertedCard> cards) {
        this.cards = cards;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRankValue() {
        return rankValue;
    }

    public void setRankValue(int rankValue) {
        this.rankValue = rankValue;
    }

    public int[] getHighCards() {
        return highCards;
    }

    public void setHighCards(int[] highCards) {
        this.highCards = highCards;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                ", rank=" + rank +
                ", rankValue=" + rankValue +
                ", highCards=" + highCards.toString() +
                '}';
    }
}
