/**
 * Created by eolochr on 2016-08-26.
 */
public class Chessboard
{

    public static String ourChessBoard[][] =
            { // 0 1 2 3 4 5 6 7 col
                    {"r", "k", "b", "q", "a", "b", "k", "r"},// 0
                    {"p", "p", "p", "p", " ", "p", "p", "p"},// 1
                    {" ", " ", " ", " ", "q", " ", " ", " "},// 2
                    {" ", " ", " ", " ", " ", " ", " ", " "},// 3
                    {" ", " ", " ", " ", "A", " ", " ", " "},// 4
                    {" ", " ", " ", " ", " ", " ", " ", " "},// 5
                    {"P", "P", "P", "P", " ", "P", "P", "P"},// 6
                    {"R", "K", "B", "Q", " ", "B", "K", "R"} // 7 row
            };

    public static String emptySquare = " ";
}
