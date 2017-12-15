//this was used to help me realize that I was checking whether a side was cleared in the middle of each move rather than at
    //the end of a move
public class Tester2
{
    public static void main(String args[])
    {
        Game game = new Game(new int[] {0,8,4,3,1,0,10,0,0,0,0,0,15,7});
        System.out.println(game.boardString(1));
        System.out.println(game.boardString(2));
        game.move(12, 2);
        System.out.println(game.boardString(2));
        System.out.println(game.boardString(3));
    }
}
