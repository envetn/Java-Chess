package Sides;

/**
 * Created by eolochr on 2016-08-26.
 */
public class Black
{
    String blackPossibleMoves;
    static int myKingPosition;
    public Black()
    {
        //some member
    }


    public void clearPossibleMoves()
    {
        blackPossibleMoves = "";
    }

    public String getBlackPossibleMoves()
    {
        return blackPossibleMoves + "\n";
    }

    public void calculateMoves(int position, String chessBoard[][])
    {
        switch (chessBoard[position / 8][position % 8])
        // 55 / 8 ~ 6 || 55 % 8 = 7
        {
            case "p":
                blackPossibleMoves += possibleP(position, chessBoard);
                break;
            case "r":
                blackPossibleMoves += possibleR(position, chessBoard);
                break;
            case "k":
                blackPossibleMoves += possibleK(position, chessBoard);
                break;
            case "b":
                blackPossibleMoves += possibleB(position, chessBoard);
                break;
            case "q":
                blackPossibleMoves += possibleQ(position, chessBoard);
                break;
            case "a":
                blackPossibleMoves += possibleA(position, chessBoard);
                break;
        }
    }

    public static String possibleP(int value, String chessBoard[][])
    {
        String list = "", oldPiece = "";
        int r = value / 8, c = value % 8;
        try
        {

            if (" " == chessBoard[r + 1][c])
            {
                if (" " == chessBoard[r + 2][c] && r == 1)
                {
                    oldPiece = chessBoard[r + 2][c];
                    chessBoard[r][c] = "";
                    chessBoard[r + 2][c] = "p";
                    if (kingSafe())
                    {
                        list = list + (r) + c + (r + 2) + (c) + oldPiece;
                    }
                    chessBoard[r][c] = "p";
                    chessBoard[r + 2][c] = oldPiece;
                }

                oldPiece = chessBoard[r + 1][c];
                chessBoard[r][c] = "";
                chessBoard[r + 1][c] = "p";
                if (kingSafe())
                {
                    list = list + (r) + c + (r + 1) + (c) + oldPiece;
                }
                chessBoard[r][c] = "p";
                chessBoard[r + 1][c] = oldPiece;
            }
        }
        catch (Exception e)
        {
        }
        for (int i = -1; i <= 1; i += 2) // either 1 or -1
        {
            try
            {
                if (Character.isUpperCase(chessBoard[r + 1][c + 1 * i]
                        .charAt(0)))
                {
                    oldPiece = chessBoard[r + 1][c + 1 * i]; // save
                    // target-piece
                    chessBoard[r][c] = " "; // clear current square
                    chessBoard[r + 1][c + 1 * i] = "p"; // place current piece
                    // on target-square
                    if (kingSafe())
                        list += (r) + "" + c + "" + (r + 1) + (c + 1 * i)
                                + oldPiece; // If king is free from harm, valid
                    // move

                    chessBoard[r][c] = "p"; // Return piece to start square
                    chessBoard[r + 1][c + 1 * i] = oldPiece; // Return target
                    // piece to
                    // start square
                }
            }
            catch (Exception e)
            {
            }
        }
        return list;
    }
    int tmp = 1;

    public static String possibleR(int value, String chessBoard[][])
    {
        String list = "", oldPiece = "";
        int r = value / 8, c = value % 8;
        int tmp = 1;
        for (int i = -1; i <= 1; i++)
        {
            try
            {

                while (" ".equals(chessBoard[r + tmp * i][c]))// row
                {
                    oldPiece = chessBoard[r + tmp * i][c];
                    chessBoard[r][c] = " ";
                    chessBoard[r + tmp * i][c] = "r";
                    if (kingSafe())
                    {
                        list = list + r + c + (r + tmp * i) + (c) + oldPiece;
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r + tmp * i][c] = oldPiece;
                    tmp++;
                }
                int currentRow = r + tmp * i;
                if (Character.isUpperCase(chessBoard[currentRow][c].charAt(0))) //
                {
                    oldPiece = chessBoard[currentRow][c];
                    chessBoard[r][c] = " ";
                    chessBoard[currentRow][c] = "r";
                    if (kingSafe())
                    {
                        list = list + r + c + "" + (currentRow) + (c)
                                + oldPiece;
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[currentRow][c] = oldPiece;
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
                    chessBoard[r][c + tmp * i] = "r";
                    if (kingSafe())
                    {
                        list = list + r + c + "" + (r) + (c + tmp * i)
                                + oldPiece;
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r][c + tmp * i] = oldPiece;
                    tmp++;
                }
                if (Character.isUpperCase(chessBoard[r][c + tmp * i].charAt(0))) //
                {
                    oldPiece = chessBoard[r][c + tmp * i];
                    chessBoard[r][c] = " ";
                    chessBoard[r][c + tmp * i] = "r";
                    if (kingSafe())
                    {
                        list = list + r + c + (r) + (c + tmp * i) + oldPiece;
                    }
                    chessBoard[r][c] = "r";
                    chessBoard[r][c + tmp * i] = oldPiece;
                }

            }
            catch (Exception e)
            {
            }
        }
        return list;
    }

    public static String possibleK(int value, String chessBoard[][])
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
                            || Character.isUpperCase(chessBoard[r + tmp * i][c
                            + tmp * k].charAt(0)))
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "k";
                        if (kingSafe())
                        {
                            list += r + "" + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "k";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;

                    }

                }
                catch (Exception e)
                {
                }
                try
                {
                    // change place on i and k
                    if (" "
                            .equals(chessBoard[r + tmp * k][c + tmp * i])
                            || Character.isUpperCase(chessBoard[r + tmp * k][c
                            + tmp * i].charAt(0)))
                    {

                        oldPiece = chessBoard[r + tmp * k][c + tmp * i];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * k][c + tmp * i] = "k";
                        if (kingSafe())
                        {
                            list = list + r + "" + c + "" + (r + tmp * k)
                                    + (c + tmp * i) + oldPiece;
                        }

                        chessBoard[r][c] = "k";
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

    public static String possibleB(int value, String chessBoard[][])
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
                        chessBoard[r + tmp * i][c + tmp * k] = "b";
                        if (kingSafe())
                        {
                            list = list + r + c + (r + tmp * i) + (c + tmp * k)
                                    + oldPiece;
                        }
                        chessBoard[r][c] = "b";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
                        tmp++;

                    }
                    if (Character.isUpperCase(chessBoard[r + tmp * i][c + tmp
                            * k].charAt(0))) //
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "b";
                        if (kingSafe())
                        {
                            list = list + r + c + (r + tmp * i) + (c + tmp * k)
                                    + oldPiece;
                        }
                        chessBoard[r][c] = "b";
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

    public static String possibleQ(int value, String chessBoard[][])
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
                        chessBoard[r + tmp * i][c + tmp * k] = "q";
                        if (kingSafe())
                        {
                            list = list + r + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "q";
                        chessBoard[r + tmp * i][c + tmp * k] = oldPiece;
                        tmp++;
                    }
                    if (Character.isUpperCase(chessBoard[r + tmp * i][c + tmp * k].charAt(0))) //
                    {
                        oldPiece = chessBoard[r + tmp * i][c + tmp * k];
                        chessBoard[r][c] = " ";
                        chessBoard[r + tmp * i][c + tmp * k] = "q";
                        if (kingSafe())
                        {
                            list = list + r + c + "" + (r + tmp * i)
                                    + (c + tmp * k) + oldPiece;
                        }
                        chessBoard[r][c] = "q";
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

    public static String possibleA(int value, String chessBoard[][])
    {
        String list = "", oldPiece;
        int r = value / 8, c = value % 8;

        for (int i = 0; i < 9; i++)
        {
            if (i != 4)// position king is standing on
            {
                try
                {
                    if (Character.isUpperCase(chessBoard[r - 1 + i / 3][c - 1
                            + i % 3].charAt(0))
                            || " ".equals(chessBoard[r - 1 + i / 3][c
                            - 1 + i % 3]))
                    {
                        oldPiece = chessBoard[r - 1 + i / 3][c - 1 + i % 3];
                        chessBoard[r - 1 + i / 3][c - 1 + i % 3] = " ";
                        chessBoard[r - 1 + i / 3][c - 1 + i % 3] = "A";
                        int kingTmp = myKingPosition;
                        if (true)
                        {
                            list = list + r + c + (r - 1 + i / 3)
                                    + (c - 1 + i % 3) + oldPiece;
                        }
                        chessBoard[r][c] = "a";
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


}
