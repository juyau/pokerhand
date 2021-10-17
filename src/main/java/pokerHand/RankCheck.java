package pokerHand;

import pokerHand.enums.RanksEnum;
import pokerHand.model.Hand;


public class RankCheck {

    // combine flush(6), straightFlush(9) and royalFlush(10);
    public static Hand getAllFlush(Hand hand){
        // check flush
        char[] suitList = RankCheck.getSuitList(hand);
        for (int i = 0; i < 4; i++) {
            if(suitList[i] != suitList[i + 1]) return null;
        }

        // check straight
        Hand straightHand = RankCheck.getStraight(hand);
        if(straightHand != null){
            // check royal
            if(straightHand.getRankValue() == 14){
                hand.setRank(RanksEnum.ROYAL_FLUSH.getRank());
                return hand;
            }
            hand.setRank(RanksEnum.STRAIGHT_FLUSH.getRank());
            hand.setRankValue(straightHand.getRankValue());
            return hand;
        }

        hand.setRank(RanksEnum.FLUSH.getRank());
        hand.setRankValue(hand.getCards().get(4).getValue());
        return hand;
    }

    public static Hand getFourOfKind(Hand hand){
        int[] valueList = RankCheck.getValueList(hand);
        for (int i = 0; i <= 1; i++) {
            if(valueList[i] == valueList[i + 1] && valueList[i] == valueList[i + 2] && valueList[i] == valueList[i + 3]
            ) {
                hand.setRank(RanksEnum.FOUR_OF_A_KIND.getRank());
                hand.setRankValue(valueList[i]);
                return hand;
            }
        }
        return null;
    }

    // combine fullHouse(7) and ThreeOfKind(4) to improve performance;
    public static Hand getFullHouseOrThreeOfKind(Hand hand){
        int[] valueList = RankCheck.getValueList(hand);
        for (int i = 0; i <= 2; i++) {
            if(valueList[i] == valueList[i + 1] && valueList[i] == valueList[i + 2]) {
                if(i==0 && valueList[3] == valueList[4]){
                   hand.setRank(RanksEnum.FULL_HOUSE.getRank());
                   hand.setRankValue(valueList[i]);
                    return hand;
                }else if(i==2 && valueList[0] == valueList[1]) {
                    hand.setRank(RanksEnum.FULL_HOUSE.getRank());
                    hand.setRankValue(valueList[i]);
                    return hand;
                } else {
                    hand.setRank(RanksEnum.THREE_OF_A_KING.getRank());
                    hand.setRankValue(valueList[i]);
                    return hand;
                }
            }
        }
        return null;
    }

    public static Hand getStraight(Hand hand){
        int[] valueList = RankCheck.getValueList(hand);
        for (int i = 0; i < 4; i++) {
            if(valueList[i] != (valueList[i + 1] - 1)) return null;
        }
        hand.setRank(RanksEnum.STRAIGHT.getRank());
        hand.setRankValue(valueList[4]);
        System.out.println("straight method valid");
        return hand;
    }

    public static Hand getTwoPair(Hand hand){
        int[] valueList = RankCheck.getValueList(hand);
        if (valueList[0] == valueList[1] && valueList[2] == valueList[3]){
            hand.setRank(RanksEnum.TWO_PAIRS.getRank());
            hand.setRankValue(valueList[2]);
            hand.setHighCards(new int[]{valueList[4]});
            return hand;
        }else if(valueList[0] == valueList[1] && valueList[3] == valueList[4]){
            hand.setRank(RanksEnum.TWO_PAIRS.getRank());
            hand.setRankValue(valueList[3]);
            hand.setHighCards(new int[]{valueList[2]});
            return hand;
        }else if(valueList[1] == valueList[2] && valueList[3] == valueList[4]){
            hand.setRank(RanksEnum.TWO_PAIRS.getRank());
            hand.setRankValue(valueList[3]);
            hand.setHighCards(new int[]{valueList[0]});
            return hand;
        }
        return null;
    }

    public static Hand getPair(Hand hand){
        int[] valueList = RankCheck.getValueList(hand);
        int[] highCards;
        for (int i = 0; i < 4; i++) {
            if(valueList[i] == valueList[i + 1]) {
                hand.setRank(RanksEnum.PAIR.getRank());
                hand.setRankValue(valueList[i]);
                if(i==0){
                    highCards = new int[]{valueList[4], valueList[3], valueList[2]};
                }else if(i==1){
                    highCards = new int[]{valueList[4],valueList[3],valueList[0]};
                }else if(i==2){
                    highCards = new int[]{valueList[4],valueList[1],valueList[0]};
                }else {
                    highCards = new int[]{valueList[2],valueList[1],valueList[0]};
                }
                hand.setHighCards(highCards);
                return hand;
            }
        }
        return null;
    }

    public static Hand getHighCard(Hand hand){
        int[] valueList = RankCheck.getValueList(hand);
        hand.setHighCards(new int[]{
                valueList[4],valueList[3],valueList[2],valueList[1],valueList[0]
        });
        return hand;
    }

    public static int[] getValueList(Hand hand){
        int[] valueList = new int[5];
        for (int i = 0; i < 5; i++) {
            valueList[i] = hand.getCards().get(i).getValue();
        }
        return valueList;
    }
    public static char[] getSuitList(Hand hand){
        char[] suitList = new char[5];
        for (int i = 0; i < 5; i++) {
            suitList[i] = hand.getCards().get(i).getSuit();
        }
        return suitList;
    }
}
