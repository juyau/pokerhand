import pokerHand.PokerGameController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class PokerHandApplication {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].endsWith(".txt")) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
                PokerGameController.playAll(bufferedReader);
                bufferedReader.close();
            } catch (IOException e){
                System.out.println("File not found.");
            }
        }else {
            PokerGameController.playTerminalInput();
        }
    }
}
