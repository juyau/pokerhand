package pokerHand;

public class PokerLineInputValidate {
    public static boolean validate(String pokerLine){
        String pattern = "([2-9TKQJA][DHSC]\\s){9}[2-9TKQJA][DHSC]";
        return pokerLine.matches(pattern);
    }
}
