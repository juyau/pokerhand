package pokerHand;


import pokerHand.model.ConvertedCard;
import pokerHand.model.Hand;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PokerManager {
    public static int getWinner(String pokerLine){
        String p1string = pokerLine.substring(0,14);
        String p2string = pokerLine.substring(15,29);

        Hand hand1 = CalculateHand(p1string);
        Hand hand2 = CalculateHand(p2string);
        return CompareHandResult(hand1, hand2);
    }

    private static int CompareHandResult(Hand hand1, Hand hand2) {
        if(hand1.getRank() > hand2.getRank()) return 1;
        if(hand1.getRank() < hand2.getRank()) return 2;

        // if rank ties, compare rankValue
        if(hand1.getRank() == hand2.getRank()){
            if(hand1.getRankValue() > hand2.getRankValue()) return 1;
            if(hand1.getRankValue() < hand2.getRankValue()) return 2;

            // if rankValue ties, compare all highCards
            if(hand1.getRankValue() == hand2.getRankValue()){
                for (int i = 0; i < hand1.getHighCards().length; i++) {
                    if(hand1.getHighCards()[i] > hand2.getHighCards()[i]) return 1;
                    if(hand1.getHighCards()[i] < hand2.getHighCards()[i]) return 2;
                }
                return 0;
            }
        }
        return 0;
    }

    public static Hand CalculateHand(String singleHandString){
        List<ConvertedCard> cards = converterAndSortCards(singleHandString);
        Hand hand = new Hand();
        hand.setCards(cards);

        // check royalFlush / straightFlush / flush;
        Hand allFlush = RankCheck.getAllFlush(hand);
        if(allFlush != null) return allFlush;

        Hand fourOfKind = RankCheck.getFourOfKind(hand);
        if(fourOfKind != null) return fourOfKind;

        Hand houseOrThreeOfKind = RankCheck.getFullHouseOrThreeOfKind(hand);
        if(houseOrThreeOfKind != null) return houseOrThreeOfKind;

        Hand straight = RankCheck.getStraight(hand);
        if(straight != null) return straight;

        Hand twoPair = RankCheck.getTwoPair(hand);
        if(twoPair != null) return twoPair;

        Hand pair = RankCheck.getPair(hand);
        if(pair != null) return pair;

        return RankCheck.getHighCard(hand);
    }

    public static List<ConvertedCard> converterAndSortCards(String singlePokerString){
        String[] strList = singlePokerString.split(" ");

        List<ConvertedCard> cards = new ArrayList<>();
        for(String str: strList){
            ConvertedCard card = new ConvertedCard();
            switch (str.charAt(0)){
                case '2': card.setValue(2); break;
                case '3': card.setValue(3); break;
                case '4': card.setValue(4); break;
                case '5': card.setValue(5); break;
                case '6': card.setValue(6); break;
                case '7': card.setValue(7); break;
                case '8': card.setValue(8); break;
                case '9': card.setValue(9); break;
                case 'T': card.setValue(10); break;
                case 'J': card.setValue(11); break;
                case 'Q': card.setValue(12); break;
                case 'K': card.setValue(13); break;
                case 'A': card.setValue(14); break;
                default: break;
            }
            card.setSuit(str.charAt(1));
            cards.add(card);
        }

        cards.sort(new Comparator<ConvertedCard>() {
            @Override
            public int compare(ConvertedCard o1, ConvertedCard o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        return cards;
    }
}
