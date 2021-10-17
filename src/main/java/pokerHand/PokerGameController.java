package pokerHand;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class PokerGameController {

    private static Integer p1Score = 0;
    private static Integer p2Score = 0;
    private static Integer tieRounds = 0;

    public static void playAll(BufferedReader bufferedReader) throws IOException {
        String pokerLine = null;
        while( (pokerLine = bufferedReader.readLine()) != null){
            int winner = playEach(pokerLine);
            if(winner == 1) p1Score++;
            if(winner == 2) p2Score++;
            if(winner == 0) tieRounds++;
        }
        showResult();
    }

    public static void playTerminalInput(){
        Scanner input = new Scanner(System.in);
        System.out.println("Input 10 cards then hit enter (example 9C 9D 8D 7C 3C 2S KD TH 9H 8H)");
        while (true){
            System.out.print("Please input: ");
            String pokerLine = input.nextLine();
            if(!PokerLineInputValidate.validate(pokerLine)){
                System.out.println("Input format incorrect.......");
                return;
            }
            int winner = PokerManager.getWinner(pokerLine);
            if (winner == 1){
                System.out.println("Player one won!");
            }else if (winner == 2) {
                System.out.println("Player two won!");
            }else {
                System.out.println("Two players tie, no one won!");
            }
        }

//        input.close();
    }

    public static int playEach(String pokerLine){
        if(!PokerLineInputValidate.validate(pokerLine)) throw new RuntimeException("Input format incorrect.");
        return PokerManager.getWinner(pokerLine);
    }

    public static void showResult(){
        System.out.println("Player 1 wins: " + p1Score + " hands");
        System.out.println("Player 2 wins: " + p2Score + " hands");
        System.out.println("Tie rounds: " + tieRounds + " rounds");
    }
}
