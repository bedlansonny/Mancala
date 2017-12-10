/*
Player 1 view:
    12 11 10 9  8  7
13                    6
    0  1  2  3  4  5

Player 2 view:
    5  4  3  2  1  0
 6                    3
    7  8  9  0  1  2
 */
public class Game
{
    int[] holes;

    public Game()
    {
        holes = new int[] {4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
    }

    //for testing purposes only
    public Game(int[] board)
    {
        holes = board;
    }

    //pick up the marbles and move them around the board. 6 is p1's goal and 13 is p2's goal
    //steal from across the table and the lone marble itself if last marble lands on 0-5 for p1, and 7-12 for p2, and across has more than 0 pieces
    //print "repeat" if landed in their hole for last marble
    //print "finished" if game is over. if all pieces are only on 0-5 or only 7-12 at the end of the turn, it's finished
    //print "invalid" if move can't be made
    //print "" if none of those happened
    public String move(int holeNum, int playerNum)
    {
        //print "invalid" if 6, 13, empty, wrong side, or out of bounds
        if(holeNum < 0 || holeNum == 6 || holeNum >= 13|| holes[holeNum] == 0 || (holeNum <= 5 && playerNum == 2) || (holeNum >= 7 && holeNum <= 12 && playerNum == 1))
            return "invalid";

        int marbles = holes[holeNum];
        holes[holeNum] = 0;
        for(int i = 0; i < marbles; i++)
        {
            holeNum = (holeNum+1)%14;
            //if index is opponent's goal, skip it
            if((holeNum == 6 && playerNum == 2) || (holeNum == 13 && playerNum == 1))
                holeNum = (holeNum+1)%14;

            holes[holeNum]++;

        }

        //if lands alone in one's own side alone, steal
        if(holeNum != 13 && holeNum != 6 && holes[holeNum] == 1 && holes[12-holeNum] > 0 && ((holeNum <= 5 && playerNum == 1)||(holeNum >=7 && holeNum <=12 && playerNum == 2)))
        {
            int goalNum;
            if(playerNum == 1)
                goalNum = 6;
            else
                goalNum = 13;

            holes[holeNum] = 0;
            holes[goalNum] += 1 + holes[12-holeNum];
            holes[12-holeNum] = 0;
        }

        if(finished())
            return "finished";

        //if last marble lands in their goal (6 or 13) return "repeat"
        if(((holeNum == 6 && playerNum == 1)||(holeNum == 13 && playerNum == 2)))
            return "repeat";

        return "";
    }

    //if finished, moves extra pieces to their goal and returns true
    public boolean finished()
    {
        //if 0-5 is empty, add 7-12 to 13 and return "finished"
        boolean bottomEmpty = true;
        for(int i = 0; i <= 5; i++)
        {
            if(holes[i] != 0)
            {
                bottomEmpty = false;
                break;
            }
        }
        if(bottomEmpty)
        {
            for(int i = 7; i <= 12; i++)
            {
                holes[13] += holes[i];
                holes[i] = 0;
            }
            return true;
        }
        //if 7-12 is empty, add 0-5 to to 6 and return "finished"
        boolean topEmpty = true;
        for(int i = 7; i <= 12; i++)
        {
            if(holes[i] != 0)
            {
                topEmpty = false;
                break;
            }
        }
        if(topEmpty)
        {
            for(int i = 0; i <= 5; i++)
            {
                holes[6] += holes[i];
                holes[i] = 0;
            }
            return true;
        }

        return false;
    }

    public String boardString(int playerNum)
    {
        String output = "";

        if(playerNum == 1)
        {
            output += "    "+holes[12]+"  "+holes[11]+"  "+holes[10]+"  "+holes[9]+"  "+holes[8]+"  "+holes[7]+"\n";
            output += " "+holes[13]+"                    "+holes[6]+"\n";
            output += "    "+holes[0]+"  "+holes[1]+"  "+holes[2]+"  "+holes[3]+"  "+holes[4]+"  "+holes[5]+"\n";
            output += "    a  b  c  d  e  f\n";
            return output;
        }
        else if(playerNum == 2)
        {
            output += "    "+holes[5]+"  "+holes[4]+"  "+holes[3]+"  "+holes[2]+"  "+holes[1]+"  "+holes[0]+"\n";
            output += " "+holes[6]+"                    "+holes[13]+"\n";
            output += "    "+holes[7]+"  "+holes[8]+"  "+holes[9]+"  "+holes[10]+"  "+holes[11]+"  "+holes[12]+"\n";
            output += "    a  b  c  d  e  f\n";
            return output;
        }
        else if(playerNum == 3)
        {
            int player1score = holes[6];
            int player2score = holes[13];
            int winner;
            if(player1score > player2score)
                winner = 1;
            else
                winner = 2;

            output += "Player " + winner + " won " + player1score + " to " + player2score + "!\n";
            output += "    "+holes[12]+"  "+holes[11]+"  "+holes[10]+"  "+holes[9]+"  "+holes[8]+"  "+holes[7]+"\n";
            output += " "+holes[13]+"                    "+holes[6]+"\n";
            output += "    "+holes[0]+"  "+holes[1]+"  "+holes[2]+"  "+holes[3]+"  "+holes[4]+"  "+holes[5]+"\n";
            return output;
        }
        else
        {
            return "Error, invalid argument in boardString method.";
        }
    }

}
