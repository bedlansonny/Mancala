import java.io.*;
import java.util.*;

public class Tester
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        Game game = new Game();

        byte playerNum = 1;
        do
        {
            System.out.println("\n\n");
            System.out.println(game.boardString(playerNum));
            System.out.print("Player " + playerNum + ", make your move: ");
            char input = in.next().charAt(0);

            int charMod;
            if(playerNum == 1)
                charMod = 97;
            else
                charMod = 90;

            String result = game.move((int)(input-charMod), playerNum);

            if(result == "invalid")
            {
                System.out.println("Invalid move. Try again.");
                continue;
            }
            if(result == "repeat")
                continue;
            if(result == "finished")
                break;
            //[else] switch player
                playerNum = (byte)(playerNum%2 +1);
        } while(true);

        //print result
        System.out.println("\n\n");
        System.out.println(game.boardString(3));
    }
}
