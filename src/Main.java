import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

//import javax.swing.*;
public class Main 
{
    /*static String chessBoard[][]=
    {  // 0   1   2   3   4   5   6   7 col
        {"r","k","b","a","a","b","k","r"},//0
        {"p","p","p","p","p","p","p","p"},//1
        {" "," "," "," "," "," "," "," "},//2
        {" "," "," "," "," "," "," "," "},//3
        {" "," "," "," "," "," "," "," "},//4
        {" "," "," "," "," "," "," "," "},//5
        {"P","P","P","P","P","P","P","P"},//6
        {"R","K","B","Q","A","B","K","R"} //7 row
     };*/
    static int kingPosC = 42, kingPosL = 03;
    public static Interface u_interface;
    public static Movements movement;
    public static  boolean turnC = true;
    public static boolean checkC = false;
    public static boolean checkL = false;
   
    public static void main(String[] args)
    {
    	u_interface = new Interface();
    	movement = new Movements();
    	 Scanner in = new Scanner(System.in);
    	 
    	 String userInput = "";
		JFrame frame = new JFrame();
		
		frame.add(u_interface);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.show();
		
		
		while(1==1)
		{
			Movements.makeMove(in.nextLine());
		}
		
    }
}