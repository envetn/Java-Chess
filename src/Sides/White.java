package Sides;

import static java.lang.Character.isLowerCase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by eolochr on 2016-08-26.
 */
public class White
{
    String whitePossibleMoves;
    int myKingPosition;
    boolean isKingSafe = true;

    public White()
    {

        //some members
    }

    public void clearPossibleMoves()
    {
        whitePossibleMoves = "";
    }

    public void calculateMoves(int position, String chessBoard[][])
    {
        switch (chessBoard[position / 8][position % 8])
        // 55 / 8 ~ 6 || 55 % 8 = 7
        {
            case "P":
                whitePossibleMoves += possibleP(position, chessBoard);
                break;
            case "R":
                whitePossibleMoves += possibleR(position, chessBoard);
                break;
            case "K":
                whitePossibleMoves += possibleK(position, chessBoard);
                break;
            case "B":
                whitePossibleMoves += possibleB(position, chessBoard);
                break;
            case "Q":
                whitePossibleMoves += possibleQ(position, chessBoard);
                break;
            case "A":
                whitePossibleMoves += possibleA(position, chessBoard);
                break;
        }
    }

    public String getWhitePossibleMoves()
    {
        return whitePossibleMoves + "\n";
    }

    /**
     * 0120 0122 0625 0627 1030 1020 1131
     * <p>
     * If move 01 has two digits and char 'A' after then kingC is in check
     */
    public boolean kingSafe(String possibleMoves)
    {
        Pattern pattern = Pattern.compile("[0-9]{4}A");
        Matcher matcher = pattern.matcher(possibleMoves);
        if (!isKingSafe && matcher.find())
        {
            System.out.println("King C is in check : " + matcher.group(0)); //prints /{item}/
            isKingSafe = true;
            return false; //not valid move
        }
        else if (isKingSafe && !matcher.find())
        {
            isKingSafe = false;
            return true; //valid move
        }
        return true;
    }

    private String possibleA(int value, String[][] chessBoard)
    {
        String list = "", oldPiece;
        int r = value / 8, c = value % 8;

        for (int i = 0; i < 9; i++)
        {
            if (i != 4)// position king is standing on
            {
                try
                {
                    if (Character.isLowerCase(chessBoard[r - 1 + i / 3][c - 1
                            + i % 3].charAt(0))
                            || " ".equals(chessBoard[r - 1 + i / 3][c
                            - 1 + i % 3]))
                    {
                        oldPiece = chessBoard[r - 1 + i / 3][c - 1 + i % 3];
                        chessBoard[r - 1 + i / 3][c - 1 + i % 3] = " ";
                        chessBoard[r - 1 + i / 3][c - 1 + i % 3] = "A";
                        int kingTmp = myKingPosition;
                        if (kingSafe())
                        {
                            list = list + r + c + (r - 1 + i / 3)
                                    + (c - 1 + i % 3) + oldPiece;
                        }
                        chessBoard[r][c] = "A";
                        chessBoard[r - 1 + i / 3][c - 1 + i % 3] = oldPiece;
                        myKingPosition = kingTmp;
                    }
                }
                catch (Exception e)
                {
                }
            }
        }
        return list;
    }

    private static boolean kingSafe()
    {
        return true;
    }

    private String possibleQ(int value, String[][] chessBoard)
    {
        String list = "", oldPiece;
        int r = value / 8, c = value % 8;
        int tmp = 1;
        for (int i = -1; i <= 1; i++) // -1 and +1 is direction left right
        {
            for (int k = -1; k <= 1; k++)
            {
                try
                {
                    while (" ".equals(chessBoard[r + tmp * i][c + tmp * k]))
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "Q";
                        if (kingSafe())
                        {
                            list = list + r + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "Q";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
                        tmp++;
                    }
                    if (Character.isLowerCase(chessBoard[r + tmp * i][c + tmp * k].charAt(0))) //
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "Q";
                        if (kingSafe())
                        {
                            list = list + r + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "Q";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
                    }
                }
                catch (Exception e)
                {/* all out of the "board" is captured here */
                }
                tmp = 1;
            }

        }
        return list;
    }

    private String possibleB(int value, String[][] chessBoard)
    {
        String list = "", oldPiece;
        int r = value / 8, c = value % 8;
        int tmp = 1;
        for (int i = -1; i <= 1; i += 2) // -1 and +1 is direction left right
        {
            for (int k = -1; k <= 1; k += 2)
            {
                try
                {
                    while (" ".equals(chessBoard[r + tmp * i][c + tmp
                            * k]))
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "B";
                        if (kingSafe())
                        {
                            list = list + r + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "B";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
                        tmp++;

                    }
                    if (Character.isLowerCase(chessBoard[r + tmp * i][c + tmp
                            * k].charAt(0))) //
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "B";
                        if (kingSafe())
                        {
                            list = list + r + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "B";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
                    }
                }
                catch (Exception e)
                {
                }
                tmp = 1;
            }

        }
        return list;
    }

    private String possibleK(int value, String[][] chessBoard)
    {
        String list = "", oldPiece = "";
        int r = value / 8, c = value % 8;
        int tmp = 1;
        for (int i = -2; i <= 2; i += 4)
        {
            for (int k = -1; k <= 1; k += 2)
            {
                try
                {

                    if (" "
                            .equals(chessBoard[r + tmp * i][c + tmp * k])
                            || Character.isLowerCase(chessBoard[r + tmp * i][c
                            + tmp * k].charAt(0)))
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "K";
                        if (kingSafe())
                        {
                            list += r + "" + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "K";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;

                    }
                }
                catch (Exception e)
                {
                }

                try
                {
                    if (" ".equals(chessBoard[r + tmp * k][c + tmp * i])
                            || Character.isLowerCase(chessBoard[r + tmp * k][c
                            + tmp * i].charAt(0)))
                    {
                        oldPiece = chessBoard[r + tmp * k][c + tmp * i];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * k][c + tmp * i] = "K";
                        if (kingSafe())
                        {
                            list += r + "" + c + "" + (r + tmp * k)
                                    + (c + tmp * i) + oldPiece;
                        }
                        chessBoard[r][c] = "K";
                        chessBoard[r + tmp * k][c + tmp * i] = oldPiece;
                    }
                }
                catch (Exception e)
                {
                }
            }
        }
        return list;
    }

    private static String possibleP(int value, String chessBoard[][])
    {
        String list = "", oldPiece = "";
        int r = value / 8, c = value % 8;
        try
        {
            if (" ".equals(chessBoard[r - 1][c]))
            {
                if (" ".equals(chessBoard[r - 2][c]) && r == 6)
                {
                    oldPiece = chessBoard[r - 2][c];
                    chessBoard[r][c] = "";
                    chessBoard[r - 2][c] = "P";
                    if (kingSafe())
                    {
                        list = list + (r) + c + (r - 2) + (c) + oldPiece;
                    }
                    chessBoard[r][c] = "P";
                    chessBoard[r - 2][c] = oldPiece;
                }
                oldPiece = chessBoard[r - 1][c];
                if (kingSafe())
                {
                    list = list + (r) + "" + c + "" + (r - 1) + (c) + oldPiece;
                }
            }
        }
        catch (Exception e)
        {
        }

        for (int i = -1; i <= 1; i += 2) // either 1 or -1
        {
            try
            {
                if (isLowerCase(chessBoard[r - 1][c + 1 * i].charAt(0)))
                {
                    oldPiece = chessBoard[r - 1][c + 1 * i];
                    list += (r) + "" + c + "" + (r - 1) + (c + 1 * i)
                            + oldPiece;
                }
            }
            catch (Exception e)
            {
            }
        }
        return list;
    }

    private static String possibleR(int value, String chessBoard[][])
    {
        String list = "", oldPiece;
        int r = value / 8, c = value % 8;
        int tmp = 1;
        for (int i = -1; i <= 1; i++)
        {
            try
            {
                while (" ".equals(chessBoard[r + tmp * i][c]))
                {
                    oldPiece = chessBoard[r + tmp * i][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + tmp * i][c] = "R";
                    if (kingSafe())
                    {
                        list = list + r + c + "" + (r + tmp * i) + (c)
                                + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r + tmp * i][c] = oldPiece;
                    tmp++;
                }
                if (Character.isLowerCase(chessBoard[r + tmp * i][c].charAt(0))) // column
                {
                    oldPiece = chessBoard[r + tmp * i][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + tmp * i][c] = "R";
                    if (kingSafe())
                    {
                        list = list + r + c + "" + (r + tmp * i) + (c)
                                + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r + tmp * i][c] = oldPiece;
                }
            }
            catch (Exception e)
            {
            }
            tmp = 1;
            try
            {
                while (" ".equals(chessBoard[r][c + tmp * i]))// col
                {
                    oldPiece = chessBoard[r][c + tmp * i];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + tmp * i] = "R";
                    if (kingSafe())
                    {
                        list = list + r + c + "" + (r) + (c + tmp * i)
                                + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r][c + tmp * i] = oldPiece;
                    tmp++;
                }
                if (Character.isLowerCase(chessBoard[r][c + tmp * i].charAt(0))) //
                {
                    oldPiece = chessBoard[r][c + tmp * i];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + tmp * i] = "R";
                    if (kingSafe())
                    {
                        list = list + r + c + (r) + (c + tmp * i) + oldPiece;
                    }
                    chessBoard[r][c] = "R";
                    chessBoard[r][c + tmp * i] = oldPiece;
                }

            }
            catch (Exception e)
            {
            }
        }
        return list;
    }
}
