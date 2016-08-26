import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Sides.Black;
import Sides.White;

public class Movements
{

    // public static String chessBoard[][] =
    // { // 0 1 2 3 4 5 6 7 col
    // {"r", "k", "b", "q", "a", "b", "k", "r"},// 0
    // {"p", "p", "p", "p", "p", "p", "p", "p"},// 1
    // {" ", " ", " ", " ", " ", " ", " ", " "},// 2
    // {" ", " ", " ", " ", " ", " ", " ", " "},// 3
    // {" ", " ", " ", " ", " ", " ", " ", " "},// 4
    // {" ", " ", " ", " ", " ", " ", " ", " "},// 5
    // {"P", "P", "P", "P", "P", "P", "P", "P"},// 6
    // {"R", "K", "B", "Q", "A", "B", "K", "R"} // 7 row
    // };

    public static String chessBoard[][] =
            { // 0 1 2 3 4 5 6 7 col
                    { "r", "k", "b", "a", "q", "b", "k", "r" },// 0
                    { "p", "p", "p", " ", "p", "p", "p", "p" },// 1
                    { " ", " ", " ", " ", " ", " ", " ", " " },// 2
                    { " ", " ", " ", " ", " ", " ", " ", " " },// 3
                    { " ", " ", " ", " ", " ", " ", " ", " " },// 4
                    { " ", " ", " ", " ", " ", " ", " ", " " },// 5
                    { "P", "P", "P", " ", "P", "P", "P", "P" },// 6
                    { "R", "K", "B", "Q", "A", "B", "K", "R" } // 7 row
            };
    static int kingPosC, kingPosL;

    public static boolean turnC = true;
    public static boolean checkC = false;
    public static boolean checkL = false;

    private static String emptySquare = " ";
    private static String possibleMovesTmp;
    private static String regexLookForKing4 = "[0-9]{4}A";
    private static String regexLookForKing2 = "[0-9]{2}A";
    private static String possibleMovesC = "";
    private static String possibleMovesL = "";

    private static White whiteSide = new White();
    private static Black blackSide = new Black();

    public String[][] getChessBoard()
    {
        return chessBoard;
    }

    public void printChessBoard()
    {
        for (int i = 0; i < 8; i++)
        {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }

    public static void makeMove(String moves)
    {
        try
        {
            while (!"A".equals(chessBoard[kingPosC / 8][kingPosC % 8]))
            {
                kingPosC++;
            }// get King's location
        }
        catch (Exception e)
        {
        }

        // while (!"a".equals(chessBoard[kingPosL/8][kingPosL%8]))
        // {kingPosL++;}//get king's location
        possibleMovesC();
        possibleMovesL();
        if (turnC)
        {
            if (possibleMovesC.contains(moves))
            {
                int x1, y1, x2, y2;
                x1 = Character.getNumericValue(moves.charAt(2));
                y1 = Character.getNumericValue(moves.charAt(3));

                x2 = Character.getNumericValue(moves.charAt(0));
                y2 = Character.getNumericValue(moves.charAt(1));
                if (chessBoard[x2][y2] == "A")
                {
                    kingPosC = Integer.valueOf(x1 + "" + y1);

                }
                chessBoard[x1][y1] = chessBoard[x2][y2];
                chessBoard[x2][y2] = emptySquare;
                turnC = false;
            }

        }
        else
        {
            if (possibleMovesL.contains(moves))
            {
                int x1, y1, x2, y2;
                x1 = Character.getNumericValue(moves.charAt(2));
                y1 = Character.getNumericValue(moves.charAt(3));

                x2 = Character.getNumericValue(moves.charAt(0));
                y2 = Character.getNumericValue(moves.charAt(1));
                if (chessBoard[x2][y2] == "a")
                {
                    kingPosL = Integer.valueOf(x1 + "" + y1);

                }
                chessBoard[x1][y1] = chessBoard[x2][y2];
                chessBoard[x2][y2] = emptySquare;
                turnC = true;
            }

        }
    }

    public static void undoMove(String moves)
    {
        if (moves.charAt(4) != 'P') // hardcore
        {
            int x1, y1, x2, y2;
            x1 = Character.getNumericValue(moves.charAt(0));
            y1 = Character.getNumericValue(moves.charAt(1));

            x2 = Character.getNumericValue(moves.charAt(2));
            y2 = Character.getNumericValue(moves.charAt(3));
            chessBoard[x1][y1] = chessBoard[x2][y2];
            chessBoard[x2][y2] = String.valueOf(moves.charAt(4));
        }
    }

    private static void possibleMovesC()
    {

        whiteSide.clearPossibleMoves();
        for (int i = 0; i < 64; i++)
        {
            whiteSide.calculateMoves(i, chessBoard);
        }
        possibleMovesC = whiteSide.getWhitePossibleMoves();
    }

    private static void possibleMovesL()
    {
        blackSide.clearPossibleMoves();
        for (int i = 0; i < 64; i++)
        {
            blackSide.calculateMoves(i,chessBoard);
        }
        possibleMovesL = blackSide.getBlackPossibleMoves();
    }
    /* CAPITAL LETTERS */

    /**
     * Check if any of the lower letters threatens the Capital king.
     *
     */
    public static boolean kingSafe(String possibleMoves, String regex)
    {
        /**
         * 0120 0122 0625 0627 1030 1020 1131
         *
         * If move 01 has two digits and char 'A' after then kingC is in check
         */
		Pattern pattern = Pattern.compile(regexLookForKing4);
		Matcher matcher = pattern.matcher(possibleMoves);
		if (!checkC && matcher.find()) 
		{
			System.out.println("King C is in check : " + matcher.group(0)); //prints /{item}/
			checkC = true;
			return false; //not valid move
		}
		else if(checkC && !matcher.find())
		{
			checkC = false;
			return true; //valid move
		}

        //bishop/queen
        int temp = 1;
        int row, column;
        for (int i = -1; i <= 1; i += 2)
        {
            for (int j = -1; j <= 1; j += 2)
            {
                try
                {
                    row = kingPosC / 8 + temp * i;
                    column = kingPosC % 8 + temp * j;
//                    System.out.println("Checking : " + chessBoard[row][column] + " on row: " + row + " Column: " + column);
                    while (" ".equals(chessBoard[row][column]))
                    {
                        temp++;
                        row = kingPosC / 8 + temp * i;
                        column = kingPosC % 8 + temp * j;
                    }
                    if ("b".equals(chessBoard[row][column]) ||
                            "q".equals(chessBoard[row][column]))
                    {
                        return false;
                    }
                }
                catch (Exception e) {}
                temp = 1;
            }
        }
        return true;
    }

    public static void printLetterChessBoard()
    {
        // For console
        for (int i = 0; i < 8; i++)
        {
            System.out.println(Arrays.toString(chessBoard[i]));
        }

        System.out.println("-----------------------");
		/*
		 * if(turnC) System.out.println("White: " + possibleMovesC()); else
		 * System.out.println("Black: " + Main.possibleMovesL());
		 */
        possibleMovesTmp = possibleMovesL;
        System.out.println("White: " + possibleMovesC);
        System.out.println("Black: " + possibleMovesL);
    }

    public static String randomMove(String moves)
    {
        // whitespace or letter
        int length = 0;
        for (int i = 0; i < moves.length(); i++)
        {
            if (moves.charAt(i) == ' '
                    || Character.isUpperCase(moves.charAt(i)))
            {
                length++;
            }
        }
        String[] possibleMoves = new String[length + 1];
        int i = 0;
        int pos = 0;
        try
        {

            while (i <= moves.length())
            {
                if (moves.charAt(i) == ' '
                        || Character.isUpperCase(moves.charAt(i)))
                {
                    i++;
                }
                else
                {
                    possibleMoves[pos] = (Character.getNumericValue(moves
                            .charAt(i))
                            + ""
                            + Character.getNumericValue(moves.charAt(i + 1))
                            + ""
                            + Character.getNumericValue(moves.charAt(i + 2))
                            + "" + Character.getNumericValue(moves
                            .charAt(i + 3)));

                    i += 4;
                    pos++;

                }
                // randomMove("2743 7463 8394 2749 8473 9584A9483E");
            }
        }
        catch (Exception e)
        {
        }

        Random rand = new Random();

        int randomNum = rand.nextInt((6 - 0) + 1) + 0;
        System.out.println(possibleMoves[randomNum]);

        return possibleMoves[randomNum];
    }
}
